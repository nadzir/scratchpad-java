lazy val akkaHttpVersion = "10.1.4"
lazy val akkaVersion    = "2.5.15"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "scraper",
      scalaVersion := "2.12.6",
      name := "scraper"
    )),
    name := "TestProject",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      "com.typesafe.akka" %% "akka-http-jackson"    % akkaHttpVersion,
      "org.jsoup"          % "jsoup"                % "1.11.3",

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "junit"              % "junit"             % "4.12"          % Test,
      "com.novocode"       % "junit-interface"   % "0.10"          % Test
    ),

    testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
  )
