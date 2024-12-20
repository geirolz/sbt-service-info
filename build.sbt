lazy val prjName         = "sbt-service-info"
lazy val prjPackageName  = prjName.replaceAll("[^\\p{Alpha}\\d]+", ".")
lazy val prjDescription  = "A purely functional XML library"
lazy val prjOrg          = "com.github.geirolz"
lazy val prjScalaVersion = "2.12.19"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    addCommandAlias("validate", ";scalafmtSbtCheck;scalafmtCheckAll;compile;test;")
  )
  .settings(commonSettings: _*)
  .settings(
    inThisBuild(
      List(
        organization := prjOrg,
        homepage := Some(url(s"https://github.com/geirolz/$prjName")),
        licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
        developers := List(
          Developer(
            "DavidGeirola",
            "David Geirola",
            "david.geirola@gmail.com",
            url("https://github.com/geirolz")
          )
        )
      )
    )
  )
  .settings(
    pluginCrossBuild / sbtVersion := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.2.8" // set minimum sbt version
        case "2.13" => "1.2.8" // set minimum sbt version
      }
    }
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  // project
  name := prjName,
  description := prjDescription,
  organization := prjOrg,
  // scala
  scalaVersion := prjScalaVersion,
  scalacOptions ++= scalacSettings,
  Keys.versionScheme := Some("early-semver"),
  // dependencies
  resolvers ++= ProjectResolvers.all,
  libraryDependencies ++= ProjectDependencies.common,
  addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.13.1"),
  // fmt
  scalafmtOnCompile := true
)

def scalacSettings: Seq[String] =
  Seq(
    //    "-Xlog-implicits",
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
    "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
    "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
    "-Xlint:option-implicit", // Option.apply used implicit view.
    "-Xlint:package-object-classes", // Class or object defined in package object.
    "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
    "-Ywarn-dead-code", // Warn when dead code is identified.
    "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
    "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
    "-Ywarn-numeric-widen", // Warn when numerics are widened.
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused.
    "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
    //    "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
    //    "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
    //    "-Ywarn-unused:locals", // Warn if a local definition is unused.
    //    "-Ywarn-unused:explicits", // Warn if a explicit value parameter is unused.
    //    "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
    //    "-Ywarn-unused:privates", // Warn if a private member is unused.
    "-Ywarn-macros:after", // Tells the compiler to make the unused checks after macro expansion
    "-Xsource:3"
//    "-P:kind-projector:underscore-placeholders"
  )
