package com.geirolz.sbt.serviceinfo

class ServiceInfo private (
  val boundedContext: BoundedContext,
  val processingPurpose: ProcessingPurpose,
  val tags: Set[ServiceTag]
)
object ServiceInfo {

  def of(
    boundedContext: BoundedContext,
    processingPurpose: ProcessingPurpose,
    additionalTags: Set[ServiceTag] = Set.empty
  ): ServiceInfo =
    new ServiceInfo(
      boundedContext,
      processingPurpose,
      Set(
        ServiceTag.fromBoundedContext(boundedContext),
        ServiceTag.fromProcessingPurpose(processingPurpose)
      ) ++ additionalTags
    )
}
