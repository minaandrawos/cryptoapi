package com.exchanges

import java.math.BigDecimal

import com.twitter.util.{Future, FuturePool, Try}
import org.knowm.xchange.ExchangeFactory
import org.knowm.xchange.binance.BinanceExchange
import org.knowm.xchange.coinbase.CoinbaseExchange
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.dto.marketdata.Ticker
import org.knowm.xchange.kraken.KrakenExchange

case class client(name: String,pair: String) {

  val exchanges = Map(
    "binance" -> classOf[BinanceExchange].getName(),
    "kraken" -> classOf[KrakenExchange].getName(),
    "coinbase" -> classOf[CoinbaseExchange].getName()
  )

  private[this] val exchange = ExchangeFactory.INSTANCE.createExchange(exchanges(name))

  private[this] val marketDataService = exchange.getMarketDataService()

  val Array(base,counter) = pair.split("-")
  val ticker = FuturePool.unboundedPool(marketDataService.getTicker(new CurrencyPair(base, counter)))

  def getTicker = ticker

  def getLow = ticker.map(t=>t.getLow)

  def getOpen = ticker.map(t=>t.getOpen)

  def getLast = ticker.map(t=>t.getLast)

  def getBid = ticker.map(t=>t.getBid)

  def getAsk = ticker.map(t=>t.getAsk)

  def getHigh = ticker.map(t=>t.getHigh)


  def getVwap = ticker.map(t=>t.getVwap)

  def getVolume = ticker.map(t=>t.getVolume)

  def getQuoteVolume = ticker.map(t=>t.getQuoteVolume)

  def getTimestamp = ticker.map(t=>t.getTimestamp)

  def getBidSize = ticker.map(t=>t.getBidSize)

  def getAskSize = ticker.map(t=>t.getAskSize)

}
