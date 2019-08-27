# cryptoapi
An HTTP server that provides crypto coin ticker information written in Scala and Finatra

### API:
```
/<exchange name>/<ticker>/<operation: open,low,high,ticker,ask,volume>
```
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
{"currency_pair":"ETH/BTC","open":0.018170,"last":0.018210,"bid":0.018220,"ask":0.018230,"high":0.018440,"low":0.018080,"vwap":0.017717,"volume":5042.37174799,"quote_volume":91.82158953089790}
```



