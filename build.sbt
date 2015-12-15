name := "macro-recursive-minimal"


val commonSettings = Seq(
  scalaVersion := "2.11.7",
  version := "0.1-SNAPSHOT",
  scalacOptions ++= Seq("-deprecation"))

lazy val macro = project.in(file("macro")).
   settings(commonSettings: _*).
   settings(libraryDependencies ++= Seq(
     "org.scala-lang" % "scala-reflect" % scalaVersion.value
   ))

lazy val main = project.in(file("main")).
   settings(commonSettings: _*).
   dependsOn(macro)

lazy val all = project.in(file(".")).aggregate(main, macro)

