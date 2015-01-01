name := "my-scala-training"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.6",
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
  "io.spray" % "spray-can_2.10" % "1.3.2",
  "io.spray" % "spray-routing_2.10" % "1.3.2",
  "io.spray" %%  "spray-json" % "1.3.1"
)
