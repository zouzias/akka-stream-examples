name := "akka-stream-examples"

organization  := "org.zouzias"

version       := "0.1"

scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  Seq(
    "ch.qos.logback"      % "logback-classic" % "1.1.3",
    "com.typesafe.akka"   %% "akka-slf4j"     % akkaV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",

    // Streams
    "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M2",

    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

