package com.controllers

import java.util.Date

import com.services.cryptoApiService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.{Duration, Future}
import javax.inject.Inject

class tickerController @Inject()(service: cryptoApiService) extends Controller {

  case class Response(ticker: String,exchange: String,value: String)

  case class ErrResponse(error: String)

  get("/:ex/:pair/:op") { request: Request =>
    val (ex,pair,op) = (request.getParam("ex"), request.getParam("pair"), request.getParam("op"))
    service.getMethod(ex, pair, op)
      .map{
        case v if op.equalsIgnoreCase("ticker") => v
        case v => Response(pair.replace("-","/").toUpperCase,ex,v.toString)
      }.rescue {
      case e: Exception => Future.value(ErrResponse(e.getLocalizedMessage))
    }

  }

  get("/ping") { request: Request =>
    Future.value("pong")
  }

}
