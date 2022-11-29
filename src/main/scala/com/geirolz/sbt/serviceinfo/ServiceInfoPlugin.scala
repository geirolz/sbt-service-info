package com.geirolz.sbt.serviceinfo

import com.geirolz.sbt.serviceinfo.ServiceInfoPlugin.autoImport.serviceInfo
import sbt.{settingKey, AutoPlugin, KeyRanks, Plugins, Setting, SettingKey}
import sbtbuildinfo.BuildInfoKey
import sbtbuildinfo.BuildInfoKeys.buildInfoKeys

object ServiceInfoPlugin extends AutoPlugin {

  object autoImport {
    val serviceInfo: SettingKey[ServiceInfo] =
      settingKey[ServiceInfo]("Service info")
        .withRank(KeyRanks.Invisible)

    val serviceBoundedContext: SettingKey[BoundedContext] =
      settingKey[BoundedContext]("Service bounded context info")
        .withRank(KeyRanks.Invisible)

    val serviceProcessingPurpose: SettingKey[ProcessingPurpose] =
      settingKey[ProcessingPurpose]("Service processing purpose")
        .withRank(KeyRanks.Invisible)

    val serviceTags: SettingKey[Set[ServiceTag]] =
      settingKey[Set[ServiceTag]]("Service tags")
        .withRank(KeyRanks.Invisible)
  }

  override def requires: Plugins = sbtbuildinfo.BuildInfoPlugin

  override def projectSettings: Seq[Setting[?]] = Seq(
    buildInfoKeys ++= Map(
      "boundedContext"    -> serviceInfo.value.boundedContext,
      "processingPurpose" -> serviceInfo.value.processingPurpose,
      "tags"              -> serviceInfo.value.tags.toList.map(_.value)
    ).toList.map(BuildInfoKey(_))
  )
}
