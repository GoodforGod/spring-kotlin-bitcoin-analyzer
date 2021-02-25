package com.arrival.crypto.bitcoinanalyzer.controller

import com.arrival.crypto.bitcoinanalyzer.service.BlockHashCalculator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

/**
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
@Controller("/bitcoin/block")
class BitcoinBlockController(private val hashCalculator: BlockHashCalculator) {

    @GetMapping("/subhash")
    fun getSubHash(from: Long, to: Long): Mono<String> {
        if(from < 0)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'from' block height can't be 0 or less"))
        if(to < from)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "'to' block height can't be less than 'from'"))
        if((to - from) > 100)
            return Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "Range can not be more than 100 blocks per request"))

        return hashCalculator.getLongestSubHashByRange(from, to)
    }
}