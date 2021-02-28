package com.arrival.crypto.bitcoinanalyzer.service

import com.arrival.crypto.bitcoinanalyzer.model.Block
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
class DecodedBlockHashCalculatorTests(@Autowired val calculator: DecodedBlockHashCalculator) : Assertions() {

    @Test
    fun `calculate existing sub hash for blocks`() {
        val blocks = listOf(
            Block(646940L, 1, 1, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6"),
            Block(646941L, 1, 2, "0000000000000000000ecb9d7b39484bab8bde23859ce59a22ffef46735fe95f")
        )

        val subHashByRange = calculator.getLongestSubHash(blocks).block(Duration.ofSeconds(5))
        assertEquals("2989", subHashByRange)
    }

    @Test
    fun `calculate non existing sub hash for blocks`() {
        val blocks = listOf(
            Block(646941L, 1, 1, "0000000000000000000ecb9d7b39484bab8bde23859ce59a22ffef46735fe95f"),
            Block(646942L, 1, 2, "0000000000000000000c3460e3fc89c6a20fcb4f35cbd1fe1f99d1a9ed160868")
        )

        val subHashByRange = calculator.getLongestSubHash(blocks).block(Duration.ofSeconds(5))
        assertNull(subHashByRange)
    }
}