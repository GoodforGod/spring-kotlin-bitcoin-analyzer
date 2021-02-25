package com.arrival.crypto.bitcoinanalyzer.service.impl

import com.arrival.crypto.bitcoinanalyzer.btc.BtcClient
import com.arrival.crypto.bitcoinanalyzer.model.Block
import com.arrival.crypto.bitcoinanalyzer.service.BlockchainProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * Ensures caching when retrieving blocks for better performance
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
@Service
class BtcCachedBlockchainProvider(private val btcClient: BtcClient) : BlockchainProvider {

    override fun getBlocks(heights: Collection<Long>): Flux<Block> {
        return btcClient.getBlocks(heights)
            .map { it }
    }
}