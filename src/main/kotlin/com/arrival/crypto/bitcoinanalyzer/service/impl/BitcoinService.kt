package com.arrival.crypto.bitcoinanalyzer.service.impl

import com.arrival.crypto.bitcoinanalyzer.error.BlockchainParamException
import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import com.arrival.crypto.bitcoinanalyzer.service.BlockchainProvider
import com.arrival.crypto.bitcoinanalyzer.service.BlockchainService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
@Qualifier("bitcoin")
@Service
class BitcoinService(
    @Qualifier("bitcoin") private val provider: BlockchainProvider,
    private val calculator: BlockHashCalculator
) : BlockchainService {

    override fun getLongestSubHashByHeightRange(from: Long, to: Long): Mono<String> {
        if (from < 0)
            return Mono.error(BlockchainParamException("'from' block height can't be 0 or less."))
        if (to == from)
            return Mono.error(BlockchainParamException("'from' block height can't be equal 'to' block height."))
        if (to < from)
            return Mono.error(BlockchainParamException("'to' block height can't be less than 'from'."))

        // actually we can always take N/2 block cause each block has prev hash, its hash and next block hash
        var count = to
        val range = generateSequence { (count--).takeIf { it >= from } }.toList()
        return provider.getBlocks(range)
            .collectList()
            .flatMap { calculator.getLongestSubHash(it) }
    }
}