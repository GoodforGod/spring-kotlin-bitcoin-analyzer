package com.arrival.crypto.bitcoinanalyzer.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BitcoinServiceTests(val service: BlockchainService) : Assertions(){

    @Test
    fun `calculate sub hash for block height range existing`() {
        val subHash = service.getLongestSubHashByHeightRange(646940L, 646941L).block(Duration.ofSeconds(5))
        assertEquals("2989", subHash)
    }

    @Test
    fun `calculate sub hash for block height range not existing`() {
        val subHash = service.getLongestSubHashByHeightRange(646941L, 646942L).block(Duration.ofSeconds(5))
        assertEquals("", subHash)
    }
}