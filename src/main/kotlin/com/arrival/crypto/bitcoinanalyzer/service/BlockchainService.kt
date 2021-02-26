package com.arrival.crypto.bitcoinanalyzer.service

import reactor.core.publisher.Mono

/**
 * Blockchain related service
 *
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
interface BlockchainService {

    /**
     * Its Long cause we than can reuse most of the logic for other blockchains, where block heights > Int.MAX
     */
    fun getLongestSubHashByHeightRange(from: Long, to: Long): Mono<String>
}