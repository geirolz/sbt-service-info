import sbt._

object ProjectDependencies {

  private val catsVersion       = "2.12.0"
  private val catsEffectVersion = "3.4.1"
  private val munitVersion      = "1.0.1"

  lazy val common: Seq[ModuleID] = Seq(
    general,
    tests
  ).flatten

  private val general =
    Seq(
      "org.typelevel" %% "cats-core" % catsVersion
//      "org.typelevel" %% "cats-effect" % catsEffectVersion
    )

  private val tests = Seq(
    "org.scalameta" %% "munit" % munitVersion % Test
  )

}
