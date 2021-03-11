package com.crypto.bitcoinanalyzer.service

import com.crypto.bitcoinanalyzer.model.Block
import reactor.core.publisher.Flux

/**
 * Provides info about blockchain
 *
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
interface BlockchainProvider {

    fun getBlocks(heights: Collection<Long>): Flux<Block>
}