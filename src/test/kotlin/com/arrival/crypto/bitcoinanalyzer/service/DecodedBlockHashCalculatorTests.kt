package com.arrival.crypto.bitcoinanalyzer.service

import com.arrival.crypto.bitcoinanalyzer.service.impl.BitcoinProvider
import com.arrival.crypto.bitcoinanalyzer.service.impl.DecodedBlockHashCalculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DecodedBlockHashCalculatorTests(@Autowired val calculator: DecodedBlockHashCalculator,
                                      @Autowired val provider: BitcoinProvider) : Assertions() {

    @Test
    fun `calculate existing sub hash for blocks`() {
        val blockHeights = listOf(646940L, 646941L)
        val blocks = provider.getBlocks(blockHeights).collectList().block(Duration.ofSeconds(5))
        val subHashByRange = calculator.getLongestSubHash(blocks).block(Duration.ofSeconds(5))
        assertEquals("2989", subHashByRange)
    }

    @Test
    fun `calculate non existing sub hash for blocks`() {
        val blockHeights = listOf(646941L, 646942L)
        val blocks = provider.getBlocks(blockHeights).collectList().block(Duration.ofSeconds(5))
        val subHashByRange = calculator.getLongestSubHash(blocks).block(Duration.ofSeconds(5))
        assertEquals("", subHashByRange)
    }
}