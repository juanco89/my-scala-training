name := "my-scala-training"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "spray repo" at "http://repo.spray.io"
resolvers += "Typesafe Releases" at "http://repo.akka.io/snapshots/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3-SNAPSHOT",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "junit" % "junit" % "4.11" % "test",
  "io.spray" % "spray-can_2.11" % "1.3.2",
  "io.spray" % "spray-routing_2.11" % "1.3.2",
  "io.spray" % "spray-json_2.11" % "1.3.1"
)
