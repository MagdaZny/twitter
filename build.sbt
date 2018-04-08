name := "letShout"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  // -- Play --
  "com.typesafe.play" %% "play-ws" % "2.6.11",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.3",
  "com.typesafe.play" %% "play-ws-standalone-json" % "1.1.3",

  // -- Guice --
  "net.codingwell" %% "scala-guice" % "4.1.0",

  // -- Testing --
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.github.tomakehurst" % "wiremock" % "2.10.1" % Test,
  "org.mockito" % "mockito-core" % "2.10.0" % Test,
  "org.apache.directory.api" % "api-i18n" % "1.0.0-M18"

)

//for wiremock and scalatestplus-play compatibility
val jettyVersion = "9.2.13.v20150730"
dependencyOverrides ++= Set(
  "org.eclipse.jetty" % "jetty-server" % jettyVersion,
  "org.eclipse.jetty" % "jetty-servlet" % jettyVersion,
  "org.eclipse.jetty" % "jetty-security" % jettyVersion,
  "org.eclipse.jetty" % "jetty-servlets" % jettyVersion,
  "org.eclipse.jetty" % "jetty-continuation" % jettyVersion,
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion,
  "org.eclipse.jetty" % "jetty-xml" % jettyVersion,
  "org.eclipse.jetty" % "jetty-client" % jettyVersion,
  "org.eclipse.jetty" % "jetty-http" % jettyVersion,
  "org.eclipse.jetty" % "jetty-io" % jettyVersion,
  "org.eclipse.jetty" % "jetty-util" % jettyVersion,
  "org.eclipse.jetty.websocket" % "websocket-api" % jettyVersion,
  "org.eclipse.jetty.websocket" % "websocket-common" % jettyVersion,
  "org.eclipse.jetty.websocket" % "websocket-client" % jettyVersion
)
