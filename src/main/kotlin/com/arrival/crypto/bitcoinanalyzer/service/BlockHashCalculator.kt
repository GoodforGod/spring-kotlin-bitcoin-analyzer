package com.arrival.crypto.bitcoinanalyzer.service

import com.arrival.crypto.bitcoinanalyzer.model.Block
import reactor.core.publisher.Mono

/**
 * Service for calculating sub hash for blocks
 *
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
interface BlockHashCalculator {

    fun getLongestSubHash(blocks: Collection<Block>): Mono<String?>
}