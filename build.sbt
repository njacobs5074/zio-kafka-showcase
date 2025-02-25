import Dependencies.Libraries._

name := "zio-kafka-showcase"
version := "0.1"
scalaVersion := "2.13.4"

addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

lazy val kafka = project
  .in(file("modules/kafka"))
  .settings(
    libraryDependencies ++=
      embeddedKafka ++ logging
  )

lazy val protocol = project
  .in(file("modules/protocol"))
  .settings(libraryDependencies ++= circe)

lazy val producer = project
  .in(file("modules/producer"))
  .settings(
    libraryDependencies ++=
      zio ++ zioLogging ++ logging ++ zioConfig ++ circe ++ jackson
  )
  .dependsOn(protocol)

lazy val processor = project
  .in(file("modules/processor"))
  .settings(
    libraryDependencies ++=
      zio ++ sttp ++ zioLogging ++ logging ++ zioConfig ++ circe ++ jackson
  )
  .dependsOn(protocol)
