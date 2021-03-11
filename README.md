# Bitcoin Analyzer

[![GitHub Action](https://github.com/goodforgod/bitcoin-analyzer/workflows/Java%20CI/badge.svg)](https://github.com/GoodforGod/bitcoin-analyzer/actions?query=workflow%3A%22Java+CI%22)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=alert_status)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_bitcoin-analyzer&metric=ncloc)](https://sonarcloud.io/dashboard?id=GoodforGod_bitcoin-analyzer)

## Description

RESTful web service that calculates longest sub-hash found in more than one block hashes. 

Implementation requirements:
- Kotlin, Spring
- Also, pay attention to code quality and tests

## Run

```
./gradlew bootRun
```

## API

Service provides:
- **GET** */bitcoin/block/subhash* endpoint with **from** and **to** parameters for range selection of blocks height.

Request example:
```text
http://localhost:8080/bitcoin/block/subhash?from=646940&to=646941
```

Response example:
```json
{
  "subHash": "2989"
}
```