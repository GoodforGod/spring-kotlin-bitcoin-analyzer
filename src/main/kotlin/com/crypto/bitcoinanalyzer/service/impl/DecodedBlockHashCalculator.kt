package com.crypto.bitcoinanalyzer.service.impl

import com.crypto.bitcoinanalyzer.model.Block
import com.crypto.bitcoinanalyzer.service.BlockHashCalculator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Block's decoded sub hash calculator service
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
@Service
class DecodedBlockHashCalculator(@Autowired val service: SubstringService) : BlockHashCalculator {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getLongestSubHash(blocks: Collection<Block>): Mono<String> {
        return if (blocks.size >= 2)
            Mono.fromCallable {
                logger.info("Calculating longest sub hash for ${blocks.size} blocks")
                blocks.distinct().map { b -> b.hashDecoded }
            }
                .map { list -> service.longestSubstring(list) }
                .filter { s -> s.isNotEmpty() }
                .subscribeOn(Schedulers.boundedElastic())
        else
            Mono.empty()
    }
}
