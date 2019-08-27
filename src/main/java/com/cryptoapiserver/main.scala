package com.cryptoapiserver



import com.twitter.app.App
import ch.qos.logback.classic.LoggerContext
import com.exchanges.client
import com.twitter.util.{Await, Future, FuturePool, Return, Throw, Try}
import org.knowm.xchange.ExchangeFactory
import org.knowm.xchange.binance.BinanceExchange
import org.knowm.xchange.coinbase.CoinbaseExchange
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.kraken.KrakenExchange
import org.slf4j.LoggerFactory



object main extends App{

  val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
  loggerContext.reset()
  /*
  val root = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[Logger]
  root.setLevel(Level.WARN)
  */
  val exchange = flag("e","binance","name of exchange")
  def main(): Unit = {
      val clt = miniClient("binance","eth-bla")
      val ts = Await.result(clt.getLow.rescue{case e:Exception=>Future.value(e.getLocalizedMessage)})
      println(ts)
      println(toCamel("hi_there"))
  }

  private def toCamel(s: String): String = {
    val split = s.split("_")
    val head = split.head(0).toUpper + split.head.slice(1,split.head.size)
    val tail = split.tail.map { x => x.head.toUpper + x.tail }
    head + tail.mkString
  }

  case class miniClient (name: String,pair: String) {
    val exchanges = Map(
      "binance" -> classOf[BinanceExchange].getName(),
      "kraken" -> classOf[KrakenExchange].getName(),
      "coinbase" -> classOf[CoinbaseExchange].getName()
    )

    val exchange = ExchangeFactory.INSTANCE.createExchange(exchanges(name))
    val marketDataService = exchange.getMarketDataService()
    val Array(base, counter) = pair.split("-")
    val ticker = FuturePool.unboundedPool(marketDataService.getTicker(new CurrencyPair(base, counter)))
    def getLow = ticker.map {
      t => t.getLow
     // case Throw(e) => e
    }
  }
}
