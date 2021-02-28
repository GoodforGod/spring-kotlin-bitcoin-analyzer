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
        if (blocks.size < 2)
            return Mono.empty()

        return Mono.fromCallable {
            blocks.distinct()
                .map { b ->
                    blocks.filter { ib -> ib != b }
                        .map { ib -> ib.hashDecoded.longestSubstring(b.hashDecoded) }
                        .maxByOrNull { hash -> hash.length }
                }
                .maxByOrNull { hash -> hash?.length ?: 0 }
                .orEmpty()
        }
            .filter { s -> !s.isNullOrEmpty() }
    }
}
