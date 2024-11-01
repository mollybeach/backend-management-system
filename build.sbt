name := "backend-management-system"
version := "0.1.0"
scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(
      // Akka HTTP
      "com.typesafe.akka" %% "akka-http" % "10.5.0",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
      "com.typesafe.akka" %% "akka-stream" % "2.8.0",
      
      // Database
      "org.postgresql" % "postgresql" % "42.5.4",
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      
      // Configuration
      "com.github.pureconfig" %% "pureconfig" % "0.17.4",
      
      // Logging
      "ch.qos.logback" % "logback-classic" % "1.4.7",
      
      // JWT
      "com.pauldijou" %% "jwt-core" % "5.0.0",
      
      // Testing
      "org.scalatest" %% "scalatest" % "3.2.15" % Test,
      "com.typesafe.akka" %% "akka-http-testkit" % "10.5.0" % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.8.0" % Test
    )
  ) 