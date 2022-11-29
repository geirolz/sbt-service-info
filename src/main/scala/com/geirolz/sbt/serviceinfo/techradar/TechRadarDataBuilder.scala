package com.geirolz.sbt.serviceinfo.techradar

trait TechRadarDataBuilder[F[_]] {
  def build: F[TechRadarData]
}
object TechRadarDataBuilder {

  def default[F[_]]: TechRadarDataBuilder[F] = new TechRadarDataBuilder[F] {
    override def build: F[TechRadarData] = ???
  }
}
