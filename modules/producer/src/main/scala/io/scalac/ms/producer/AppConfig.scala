package io.scalac.ms.producer

import zio._
import zio.config._
import zio.config.magnolia.DeriveConfigDescriptor
import zio.config.typesafe.TypesafeConfigSource
import com.typesafe.config.ConfigFactory

final case class AppConfig(bootstrapServers: String, topic: String) {
  def brokers: List[String] = bootstrapServers.split(",").toList
}

object AppConfig {
  private val descriptor = DeriveConfigDescriptor.descriptor[AppConfig]

  def load(): ZIO[system.System, Throwable, AppConfig] =
    for {
      rawConfig    <- ZIO.effect(ConfigFactory.load().getConfig("transaction-producer"))
      configSource <- ZIO.fromEither(TypesafeConfigSource.fromTypesafeConfig(rawConfig))
      env          <- ConfigSource.fromSystemEnv
      config       <- ZIO.fromEither(read(AppConfig.descriptor.from(env <> configSource)))
    } yield config
}
