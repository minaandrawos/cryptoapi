package com.cryptoapiserver

import com.controllers.tickerController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.modules.CryptoApiModules

object ServerMain extends CryptoApiServer

class CryptoApiServer extends HttpServer{
  override val modules = Seq(CryptoApiModules)
  override def configureHttp(router: HttpRouter): Unit = {
    router.add[tickerController]
  }
}
