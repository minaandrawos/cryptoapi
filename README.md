# CryptoApi
An HTTP server that provides cryptocoin trading information from several exchanges. The service is written in Scala and Finatra. The service makes use of the powerful XChange (https://github.com/knowm/XChange) java library.

### API:
```
/<exchange name>/<ticker>/<operation: open,low,high,ticker,ask,volume>
```

### Currently supported exchanges:
- Binance
- Kraken
- Coinbase

### Default server address:
```
http://<host>:8888
```
### Default Admin address:
```
http://<host>:9990
```

### Examples:
#### Example1
Request:
```
/kraken/eth-btc/low
```
Response:
```
{"ticker":"ETH/BTC","exchange":"kraken","value":"0.018080"}
```
#### Example2
Request:
```
/binance/xrp-btc/ticker
```
Response:
```
{"currency_pair":"XRP/BTC","open":0.00002630,"last":0.00002639,"bid":0.00002639,"ask":0.00002641,"high":0.00002653,"low":0.00002513,"vwap":0.00002605,"volume":52012380.00000000,"quote_volume":1354.93283077,"bid_size":3062.00000000,"ask_size":2238.00000000}
```



