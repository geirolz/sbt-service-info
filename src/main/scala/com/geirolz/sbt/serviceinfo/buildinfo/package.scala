package com.geirolz.sbt.serviceinfo

import cats.Monad
import sbt.SettingKey
import sbtbuildinfo.BuildInfoKey

package object buildinfo {

  import cats.syntax.all.*

  implicit class BuildInfoKeySyntax[A](entity: BuildInfoKey.Entry[A]) {
    def map[B: Manifest](f: ((String, A)) => (String, B)): BuildInfoKey.Entry[B] =
      BuildInfoKey.map(entity)(f)

    def mapValue[B: Manifest](f: A => B): BuildInfoKey.Entry[B] =
      map { case (key, value) => (key, f(value)) }
  }

  implicit class BuildInfoKeyIterableSyntax[F[X] <: Iterable[X], A](
    entity: BuildInfoKey.Entry[F[A]]
  ) {
    def toList(implicit m: Manifest[List[A]]): BuildInfoKey.Entry[List[A]] =
      entity.mapValue[List[A]](_.toList)
  }

  implicit class BuildInfoKeyMonadSyntax[F[_]: Monad, A](
    entity: BuildInfoKey.Entry[F[A]]
  ) {
    def nestedMap[B](f: A => B)(implicit M: Manifest[F[B]]): BuildInfoKey.Entry[F[B]] =
      entity.mapValue[F[B]](_.map(f))
  }

  implicit class BuildInfoSettingKeySyntax[A](sk: SettingKey[A]) {
    def toBuildInfoKey: BuildInfoKey.Entry[A] =
      BuildInfoKey(sk)
  }
}
