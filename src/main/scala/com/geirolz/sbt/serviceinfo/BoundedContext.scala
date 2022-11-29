package com.geirolz.sbt.serviceinfo

case class BoundedContext(value: String) extends AnyVal {
  override def toString: String = value
}
object BoundedContext {
  val unknown: Unknown[BoundedContext] = BoundedContext(Unknown.stringRep)
}
