name := "my-scala-training"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "spray repo" at "http://repo.spray.io"
resolvers += "Typesafe Releases" at "http://repo.akka.io/snapshots/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3-SNAPSHOT",
  "com.typesafe.akka" %% "akka-testkit" % "2.3-SNAPSHOT",
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "junit" % "junit" % "4.11" % "test",
  "io.spray" % "spray-can_2.11" % "1.3.2",
  "io.spray" % "spray-routing_2.11" % "1.3.2",
  "io.spray" % "spray-json_2.11" % "1.3.1",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "io.reactivex" %% "rxscala" % "0.24.1"
)