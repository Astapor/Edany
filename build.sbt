name := "Edany"

version := "0.1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.typesafe.akka" %% "akka-contrib" % "2.2.3",
  "org.scalatest" % "scalatest_2.10" % "2.0.RC2" % "test",
  "org.scalaz" %% "scalaz-core" % "7.0.6"
)

org.scalastyle.sbt.ScalastylePlugin.Settings

//LWJGLPlugin.lwjglSettings