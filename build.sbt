lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-service-info",
    organization := "com.geirolz.sbt",
    version := "0.1-SNAPSHOT",
    addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.11.0"),
    pluginCrossBuild / sbtVersion := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.2.8" // set minimum sbt version
        case "2.13" => "1.2.8" // set minimum sbt version
      }
    }
  )
