package com.cryptoapiserver

import com.exchanges.client
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import org.scalatest.Matchers
import org.scalatest.mockito.MockitoSugar

class CryptoApiFeatureTest extends FeatureTest with MockitoSugar with Matchers {
  val MockedClient: client = mock[client]
  override val server = new EmbeddedHttpServer(new CryptoApiServer)
    .bind[client]
    .toInstance(MockedClient)

  test("Server health...") {
    server.isHealthy should be(true)
  }

  test("Validate ping") {
    val response = server.httpGet(path = "/ping", andExpect = Status.Ok)
    response.contentString should equal("pong")
  }

  test("Validate Kraken") {
    val response =
      server.httpGet(path = "/kraken/eth-btc/low", andExpect = Status.Ok)
    assert(
      response.contentString
        .contains(""""ticker":"ETH/BTC","exchange":"kraken","value":""")
    )
  }
}
