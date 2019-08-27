package com.services

import com.exchanges.client
import com.twitter.util.{Future, Return, Throw, Try}

case class cryptoApiService() {
  def getMethod(name: String, pair: String, operation: String) = {
    getClient(name, pair).flatMap {
      clt => clt.getClass.getMethod(s"get${toCamel(operation)}").invoke(clt).asInstanceOf[Future[Any]]
    }
  }

  private def getClient(name: String, pair: String) = {
    Try(client(name, pair)) match {
      case Return(clt) => Future.value(clt)
      case Throw(e) => Future.exception(e)
    }
  }

  private def toCamel(s: String): String = {
    val split = s.split("_")
    val head = split.head(0).toUpper + split.head.slice(1, split.head.size)
    val tail = split.tail.map { x => x.head.toUpper + x.tail }
    head + tail.mkString
  }

}
