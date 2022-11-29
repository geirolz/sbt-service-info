package com.geirolz.sbt.serviceinfo

import sbt.ModuleID

final case class ServiceTag private (value: String) extends AnyVal {
  override def toString: String = value
}
object ServiceTag extends TagBuilders with TagInstances {

  def apply(value: String): ServiceTag =
    fromString(value)

  def fromString(value: String): ServiceTag =
    new ServiceTag(value.replace(" ", "-").toLowerCase())
}

sealed trait TagBuilders {

  def fromProcessingPurpose(p: ProcessingPurpose): ServiceTag =
    p match {
      case ProcessingPurpose.OLAP => tag"OLAP"
      case ProcessingPurpose.OLTP => tag"OLTP"
    }

  def fromBoundedContext(ctx: BoundedContext): ServiceTag =
    tag"bounded-context:${ctx.value}"

  def fromDependency(dep: ModuleID): ServiceTag =
    fromVersion(dep.name, dep.revision)

  def fromDependencies(deps: Seq[ModuleID]): Seq[ServiceTag] =
    deps.map(fromDependency)

  def fromScalaVersion(v: String): ServiceTag =
    fromVersion("scala", v)

  def fromVersion(sbj: String, v: String): ServiceTag =
    tag"$sbj:${v.replace(".", "_")}"

  implicit class TagStringContext(stringContext: StringContext) {
    def tag(args: Any*): ServiceTag =
      ServiceTag(stringContext.s(args*))
  }
}

sealed trait TagInstances { this: TagBuilders =>
  val microservice: ServiceTag = tag"microservice"
  val readModel: ServiceTag    = tag"read-model"

  object Languages {
    val scala: ServiceTag = tag"scala"
    val java: ServiceTag  = tag"java"
    val shell: ServiceTag = tag"shell"
    val yml: ServiceTag   = tag"yml"
  }
}
