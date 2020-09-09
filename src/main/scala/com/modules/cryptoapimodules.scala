package com.modules

import com.google.inject.Provides
import com.services.cryptoApiService
import com.twitter.inject.TwitterModule
import javax.inject.Singleton

object CryptoApiModules extends TwitterModule{
  @Singleton
  @Provides
  def providesCryptoApiService = cryptoApiService()
}
