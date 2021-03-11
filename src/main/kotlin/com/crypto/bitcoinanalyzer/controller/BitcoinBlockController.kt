package com.crypto.bitcoinanalyzer.controller

import com.crypto.bitcoinanalyzer.model.dto.HashResponse
import com.crypto.bitcoinanalyzer.service.BlockchainService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
@RestController
@RequestMapping("/bitcoin/block")
class BitcoinBlockController(@Qualifier("bitcoin") private val service: BlockchainService) {

    /**
     * We can describe contracts via swagger and provide swagger-ui for better service use\exploitation\documentation
     */
    @GetMapping("/subhash", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getSubHash(
        @RequestParam("from") from: Int,
        @RequestParam("to") to: Int
    ): Mono<HashResponse> {
        if (from < 0)
            return Mono.error(ResponseStatusException(BAD_REQUEST, "'from' block height can't be 0 or less."))
        if (to < 0)
            return Mono.error(ResponseStatusException(BAD_REQUEST, "'to' block height can't be 0 or less."))
        if ((to - from) > 100)
            return Mono.error(ResponseStatusException(BAD_REQUEST, "Range can't be more 100 blocks per request."))

        return service.getLongestSubHashByHeightRange(from.toLong(), to.toLong())
            .filter { it.isNotEmpty() }
            .map { hash -> HashResponse(hash) }
            .timeout(Duration.ofSeconds(30))
            .switchIfEmpty(
                Mono.error(
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sub hash not found for range from $from to $to"
                    )
                )
            )
            .subscribeOn(Schedulers.boundedElastic())
    }
}