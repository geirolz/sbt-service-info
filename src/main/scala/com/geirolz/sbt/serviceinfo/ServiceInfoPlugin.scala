package com.geirolz.sbt.serviceinfo

import sbt.{settingKey, AutoPlugin, Plugins, Setting, SettingKey}
import sbt.Keys.{libraryDependencies, scalaVersion}
import sbtbuildinfo.BuildInfoKey
import sbtbuildinfo.BuildInfoKeys.buildInfoKeys

import scala.collection.immutable.ListSet

object ServiceInfoPlugin extends AutoPlugin {

  import com.geirolz.sbt.serviceinfo.buildinfo.*
  import com.geirolz.sbt.serviceinfo.ServiceInfoPlugin.autoImport.*

  object autoImport {

    // settings
    val serviceBoundedContext: SettingKey[BoundedContext] =
      settingKey("Service bounded context info")

    val serviceProcessingPurpose: SettingKey[ProcessingPurpose] =
      settingKey("Service processing purpose")

    // - tags
    val serviceTags: SettingKey[Set[ServiceTag]] =
      settingKey("Service tags")

    val serviceTagsIncludeDependencies: SettingKey[Boolean] =
      settingKey("Include dependencies tags")

    val serviceTagsIncludeDependenciesVersions: SettingKey[Boolean] =
      settingKey("Include dependencies versions tags")

//    // - tech radar
//    val techRadarDataBuilder: SettingKey[TechRadarDataBuilder[IO]] =
//      settingKey("Tech radar data builder")
//
//    val techRadarOutFile: SettingKey[File] =
//      settingKey("Tech radar data output file")
//
//    // tasks
//    val buildTechRadarData = taskKey[Unit]("Build tech radar data")
  }

  override def requires: Plugins = sbtbuildinfo.BuildInfoPlugin

  override def projectSettings: Seq[Setting[?]] = Seq(
    serviceBoundedContext := BoundedContext.unknown,
    serviceProcessingPurpose := ProcessingPurpose.unknown,

    // tags
    serviceTagsIncludeDependencies := true,
    serviceTagsIncludeDependenciesVersions := true,
    serviceTags := ListSet(
      // singles
      ListSet(
        ServiceTag.Languages.scala,
        ServiceTag.fromScalaVersion(scalaVersion.value)
      ),
      // options
      ListSet(
        ServiceTag.fromBoundedContext(serviceBoundedContext.value),
        ServiceTag.fromProcessingPurpose(serviceProcessingPurpose.value)
      ).flatten,
      // collections
      if (serviceTagsIncludeDependencies.value)
        ServiceTag.fromDependencies(
          libraryDependencies.value,
          serviceTagsIncludeDependenciesVersions.value
        )
      else
        Set.empty
    ).flatten,
    buildInfoKeys ++= Seq[BuildInfoKey](
      serviceBoundedContext.toBuildInfoKey.mapValue(_.value),
      serviceProcessingPurpose.toBuildInfoKey.mapValue(_.toString),
      serviceTags.toBuildInfoKey.toList.nestedMap(_.value)
    )
//    // tech radar
//    techRadarDataBuilder := TechRadarDataBuilder.default[IO],
//    techRadarOutFile := File("techradar.ts"),
//    buildTechRadarData := techRadarDataBuilder.value.build.unsafeRunAsync
  )

}
