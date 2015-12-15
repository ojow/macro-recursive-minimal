package sample

import Macros._

import scala.collection.immutable.ListMap

object Main {

  trait SomeTrait

  class SomeCollection {
    lazy val item1 = new SomeTrait {}
    lazy val item2 = new SomeTrait {}

    //lazy val items = findLazyVals[SomeTrait]
    lazy val items: ListMap[String, SomeTrait] = findLazyVals[SomeTrait]
  }

  def main(args: Array[String]): Unit = {
    val c = new SomeCollection
    println(c.items)
  }

}
