import com.geirolz.sbt.serviceinfo._

lazy val test = (project in file("."))
  .enablePlugins(ServiceInfoPlugin)
  .settings(
    name := "sbt-service-info-test",
    organization := "com.geirolz.sbt",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.13.10",

    // info
    serviceBoundedContext := BoundedContext("USER1"),
    serviceProcessingPurpose := ProcessingPurpose.OLTP,
    // dependencies
    resolvers ++= ProjectResolvers.all,
    libraryDependencies ++= ProjectDependencies.common
  )
