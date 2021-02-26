package com.arrival.crypto.bitcoinanalyzer.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
open class AnalyzerException(status: HttpStatus, reason: String?, cause: Throwable?) : ResponseStatusException(status, reason, cause) {

    constructor(status: HttpStatus, reason: String?) : this(status, reason, null)
}

class BlockchainClientException(message: String): AnalyzerException(HttpStatus.BAD_REQUEST, message)

class BlockchainParamException(message: String): AnalyzerException(HttpStatus.BAD_REQUEST, message)