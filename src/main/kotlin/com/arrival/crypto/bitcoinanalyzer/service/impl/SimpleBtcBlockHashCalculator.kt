package com.arrival.crypto.bitcoinanalyzer.service.impl

import com.arrival.crypto.bitcoinanalyzer.model.Block
import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import com.arrival.crypto.bitcoinanalyzer.service.BlockchainProvider
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

/**
 * Straight block sub hash calculator service
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
@Service
class SimpleBtcBlockHashCalculator(private val provider: BlockchainProvider) : BlockHashCalculator{

    override fun getLongestSubHashByRange(from: Long, to: Long): Mono<String> {
        if(from < 0)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'from' block height can't be 0 or less"))
        if(to < from)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'to' block height can't be less than 'from'"))

        var count = to
        val range = generateSequence { (count--).takeIf { it > from } }.toList()
        return provider.getBlocks(range)
            .collectList()
            .flatMap { getLongestSubHash(it) }
    }

    override fun getLongestSubHash(blocks: Collection<Block>): Mono<String> {
        TODO("Not yet implemented")
    }
}