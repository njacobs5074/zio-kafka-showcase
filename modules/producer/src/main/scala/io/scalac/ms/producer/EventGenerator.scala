package io.scalac.ms.producer

import io.scalac.ms.protocol.TransactionRaw

import scala.util.Random

object EventGenerator {

  val rand = new Random()
  val countries = Array("Serbia", "Poland", "Germany", "Austria", "Sweden", "South Africa", "Mauritius")

  /** Generate N random transactions */
  def randomTransactions(count: Int = 20): Seq[TransactionRaw] = {
    for {
      i <- 1 to count
    } yield TransactionRaw(i, countries(rand.nextInt(countries.length)), BigDecimal(rand.nextDouble() * 1000.0))
  }
}
