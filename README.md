# Arrival Test Assigment

[![GitHub Action](https://github.com/goodforgod/bitcoin-analyzer/workflows/Java%20CI/badge.svg)](https://github.com/GoodforGod/bitcoin-analyzer/actions?query=workflow%3A%22Java+CI%22)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=alert_status)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=ncloc)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)

## Description

Implement RESTful web service that will analyze bitcoin blockchain block hashes. In addition to the common 
flow the service should have a proper error handling.
The service should expose an endpoint which will do the following:

- Accepts a block heights range. Maximum blocks count per request is 100
- Queries the data from the [Bitcoin blockchain API](https://btc.com/api-doc#Block) is a good way to go with for the given block height range
- Calculates the longest sub-string hash found in more than one block hashes. Just to clarify, 
  that block hash is a 256-bit number stored as a big-endian hexadecimal string. And by the "sub-string" we should understand a contiguous sequence of numbers

For example, for block heights range 646940-646941 there are two blocks with hashes 0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6 
and 0000000000000000000ecb9d7b39484bab8bde23859ce59a22ffef46735fe95f respectively.
The decoded corresponding hash numbers are: 1056850286200300937094298967488490265669752758871228406 
and 1417115074586831187112255580375243348964882211567298911 respectively.
The longest contiguous sequence of numbers for these hashes would be 2989 which should be returned by the service.

Note:
The bitcoin blockchain is a public decentralized network launched in 2009 which generates a new block every 10 minutes (approximately) 
starting from the "genesis" block i.e. a block with height = 0.
Block height is an integer number indicating a count of blocks starting from the "genesis" block.

Implementation requirements:
- Kotlin, Spring
- Also, pay attention to code quality and tests

# API

Service provides:
- **GET** */bitcoin/block/subhash* endpoint with **from** and **to** parameters for range selection of blocks height.

Request example:
```text
http://localhost:8080/bitcoin/blockl/subhash?from=646940&to=646941
```

Response example:
```json
{
  "subHash": "2989"
}
```