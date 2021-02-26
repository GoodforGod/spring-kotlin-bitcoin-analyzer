package com.arrival.crypto.bitcoinanalyzer.controller

import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import com.arrival.crypto.bitcoinanalyzer.service.BlockchainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

/**
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
@RestController("/bitcoin/block")
class BitcoinBlockController(@Qualifier("bitcoin") private val service: BlockchainService) {

    /**
     * We can describe contracts via swagger and provide swagger-ui for better service use\exploitation\documentation
     */
    @GetMapping("/subhash", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getSubHash(from: Int, to: Int): Mono<String> {
        if(from < 0)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'from' block height can't be 0 or less"))
        if(to < from)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'to' block height can't be less than 'from'"))
        if((to - from) > 100)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "Range can not be more than 100 blocks per request"))

        return service.getLongestSubHashByHeightRange(from.toLong(), to.toLong())
            .filter { it.isNotEmpty() }
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Sub hash not exist for such range")))
    }
}