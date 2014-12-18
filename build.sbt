name := "my-scala-training"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.1",
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
  "io.spray" % "spray-can" % "1.2.2",
  "io.spray" % "spray-routing" % "1.2.2",
  "io.spray" % "spray-json_2.10" % "1.2.3"
)
