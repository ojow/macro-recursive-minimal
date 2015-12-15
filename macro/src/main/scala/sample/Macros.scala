package sample

import scala.collection.immutable.ListMap
import scala.language.experimental.macros
import scala.reflect.macros.blackbox


object Macros {
  def findLazyVals[T](cls: AnyRef): ListMap[String, T] = macro findLazyValsImpl[T]

  def findLazyValsImpl[T : c.WeakTypeTag](c: blackbox.Context)(cls: c.Expr[AnyRef]): c.Expr[ListMap[String, T]] = {
    import c.universe._

//    val cls = c.typecheck(q"this")
//    val vals = cls.tpe.members.
    val tpe = weakTypeOf[T]
    val vals = cls.actualType.members.
      filter(sym => sym.isTerm && sym.asTerm.isMethod && sym.asTerm.isLazy && sym.asMethod.returnType <:< tpe).
      map(x => q"${Literal(Constant(x.name.decodedName.toString))} -> $x").
      toList.
      reverse

    c.Expr[ListMap[String, T]](q"ListMap(..$vals)")
  }
}
