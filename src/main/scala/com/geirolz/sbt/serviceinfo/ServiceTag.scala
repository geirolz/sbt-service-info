package com.geirolz.sbt.serviceinfo

import sbt.ModuleID

import scala.collection.immutable.ListSet

final case class ServiceTag private (value: String) extends AnyVal {
  override def toString: String = value
}
object ServiceTag extends TagBuilders with TagInstances {

  def apply(value: String): ServiceTag =
    fromString(value)

  def fromString(value: String): ServiceTag =
    new ServiceTag(value.replace(" ", "-"))
}

sealed trait TagBuilders {

  def fromProcessingPurpose(p: ProcessingPurpose): Option[ServiceTag] =
    p match {
      case ProcessingPurpose.OLAP    => Some(labeled("processing-purpose", "OLAP"))
      case ProcessingPurpose.OLTP    => Some(labeled("processing-purpose", "OLTP"))
      case ProcessingPurpose.Unknown => None
    }

  def fromBoundedContext(ctx: BoundedContext): Option[ServiceTag] = {
    ctx match {
      case BoundedContext.unknown => None
      case bc                     => Some(labeled("bounded-context", bc.value))
    }
  }

  def fromDependency(dep: ModuleID, addAlsoVersionedTag: Boolean = true): Set[ServiceTag] =
    ListSet(
      Some(ServiceTag(dep.name)),
      if (addAlsoVersionedTag)
        Some(versioned(dep.name, dep.revision))
      else
        None
    ).flatten

  def fromDependencies(deps: Seq[ModuleID], addAlsoVersionedTag: Boolean = true): Set[ServiceTag] =
    ListSet(deps.flatMap(fromDependency(_, addAlsoVersionedTag))*)

  def fromScalaVersion(v: String): ServiceTag =
    versioned("Scala", v)

  def versioned(sbj: String, v: String): ServiceTag =
    labeled(sbj, v.replace(".", "_"))

  def labeled(label: String, value: String): ServiceTag =
    tag"$label:$value"

  implicit class TagStringContext(stringContext: StringContext) {
    def tag(args: Any*): ServiceTag =
      ServiceTag(stringContext.s(args*))
  }
}

sealed trait TagInstances { this: TagBuilders =>
  val microservice: ServiceTag           = tag"microservice"
  val readModel: ServiceTag              = tag"read-model"
  val externalServiceGateway: ServiceTag = tag"external-service-gateway"

  object Languages {
    val scala: ServiceTag      = tag"Scala"
    val java: ServiceTag       = tag"Java"
    val kotlin: ServiceTag     = tag"Kotlin"
    val shell: ServiceTag      = tag"Shell"
    val yml: ServiceTag        = tag"Yml"
    val graphQL: ServiceTag    = tag"GraphQL"
    val javaScript: ServiceTag = tag"JavaScript"
    val openAPI: ServiceTag    = tag"OpenAPI"
    val python: ServiceTag     = tag"Python"
    val typeScript: ServiceTag = tag"TypeScript"
    val rust: ServiceTag       = tag"Rust"
    val dart: ServiceTag       = tag"Dart"
    val go: ServiceTag         = tag"Go"
  }
}
