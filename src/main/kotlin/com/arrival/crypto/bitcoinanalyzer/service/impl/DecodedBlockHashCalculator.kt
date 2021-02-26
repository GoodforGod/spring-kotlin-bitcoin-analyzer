package com.arrival.crypto.bitcoinanalyzer.service.impl

import com.arrival.crypto.bitcoinanalyzer.model.Block
import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * decoded sub hash block calculator service
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
@Service
class DecodedBlockHashCalculator : BlockHashCalculator {

    override fun getLongestSubHash(blocks: Collection<Block>): Mono<String> {
        TODO("Not yet implemented")
    }
}