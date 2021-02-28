package com.arrival.crypto.bitcoinanalyzer.service.impl

import com.arrival.crypto.bitcoinanalyzer.ext.longestSubstring
import com.arrival.crypto.bitcoinanalyzer.model.Block
import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * Block's decoded sub hash calculator service
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
@Service
class DecodedBlockHashCalculator : BlockHashCalculator {

    override fun getLongestSubHash(blocks: Collection<Block>): Mono<String> {
        return if (blocks.size >= 2)
            Mono.fromCallable {
                blocks.distinct()
                    .map { b -> b.hashDecoded }
                    .longestSubstring()
            }
                .filter { s -> s.isNotEmpty() }
        else
            Mono.empty()
    }
}
