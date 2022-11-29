package com.geirolz.sbt.serviceinfo

sealed trait ProcessingPurpose {
  override def toString: String = this match {
    case ProcessingPurpose.OLAP    => "OLAP"
    case ProcessingPurpose.OLTP    => "OLTP"
    case ProcessingPurpose.Unknown => Unknown.stringRep
  }
}
object ProcessingPurpose {

  val unknown: Unknown[ProcessingPurpose] = Unknown

  // Online analytical processing
  final case object OLAP extends ProcessingPurpose
  // Online transactional processing
  final case object OLTP extends ProcessingPurpose
  // Unknown value
  final case object Unknown extends ProcessingPurpose
}
