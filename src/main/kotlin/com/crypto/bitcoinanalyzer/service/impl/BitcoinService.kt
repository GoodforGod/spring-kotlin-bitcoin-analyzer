package com.crypto.bitcoinanalyzer.service.impl

import com.crypto.bitcoinanalyzer.error.BlockchainParamException
import com.crypto.bitcoinanalyzer.model.btc.BtcBlock
import com.crypto.bitcoinanalyzer.service.BlockHashCalculator
import com.crypto.bitcoinanalyzer.service.BlockchainProvider
import com.crypto.bitcoinanalyzer.service.BlockchainService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
@Qualifier("bitcoin")
@Service
class BitcoinService(
    @Qualifier("bitcoin") private val provider: BlockchainProvider<BtcBlock>,
    private val calculator: BlockHashCalculator
) : BlockchainService {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getLongestSubHashByHeightRange(from: Long, to: Long): Mono<String> {
        return Mono.fromCallable {
            if (from < 0)
                throw BlockchainParamException("'from' block height can't be 0 or less.")
            if (to == from)
                throw BlockchainParamException("'from' block height can't be equal 'to' block height.")
            if (to < from)
                throw BlockchainParamException("'to' block height can't be less than 'from'.")
            logger.info("Executing request in rage from $from up to $to")

            // actually we can always take N/2 block cause each block has prev hash, its hash and next block hash
            var count = to
            generateSequence { (count--).takeIf { it >= from } }.toList()
        }
            .flatMapMany { range -> provider.getBlocks(range) }
            .collectList()
            .flatMap { calculator.getLongestSubHash(it) }
            .filter { hash -> hash.isNotEmpty() }
            .subscribeOn(Schedulers.boundedElastic())
    }
}