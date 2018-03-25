name := "letShout"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.6.11",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.3",
  "com.typesafe.play" %% "play-ws-standalone-json" % "1.1.3",
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
  "com.github.tomakehurst" % "wiremock" % "2.10.1" % Test,
  "org.mockito" % "mockito-core" % "2.10.0" % Test,
  "net.codingwell" %% "scala-guice" % "4.1.0"
)

//parallelExecution in Test := false
