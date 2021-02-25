package com.arrival.crypto.bitcoinanalyzer.model.btc

/**
 * @see <a href="https://btc.com/api-doc#Response">Structure</a>
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
data class BtcMultiResponse<T>(
    val errorCode: Int,
    val errorNumber: Int,
    val message: String,
    val status: String,
    val data: List<T>
)
