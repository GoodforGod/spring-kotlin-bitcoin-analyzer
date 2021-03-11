package com.crypto.bitcoinanalyzer.model.dto

/**
 * @author GoodforGod
 * @since 28.02.2021
 */
data class ErrorResponse(val code: Int, val message: String? = "Unknown server error occurred")
