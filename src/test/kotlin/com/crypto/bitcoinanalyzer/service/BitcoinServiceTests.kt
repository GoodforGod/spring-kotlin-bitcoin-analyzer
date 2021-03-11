package com.crypto.bitcoinanalyzer.service

import com.arrival.crypto.bitcoinanalyzer.error.BlockchainParamException
import com.arrival.crypto.bitcoinanalyzer.service.impl.BitcoinService
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
class BitcoinServiceTests(@Autowired val service: BitcoinService) : Assertions() {

    @Test
    fun `calculate sub hash for block height range existing`() {
        val subHash = service.getLongestSubHashByHeightRange(646940L, 646941L).block(Duration.ofSeconds(5))
        assertEquals("2989", subHash)
    }

    @Test
    fun `range from less 0 fails`() {
        assertThrows(BlockchainParamException::class.java) {
            service.getLongestSubHashByHeightRange(-1, 9).block(Duration.ofSeconds(5))
        }
    }

    @Test
    fun `range from equal to fails`() {
        assertThrows(BlockchainParamException::class.java) {
            service.getLongestSubHashByHeightRange(10, 10).block(Duration.ofSeconds(5))
        }
    }

    @Test
    fun `range to less than from fails`() {
        assertThrows(BlockchainParamException::class.java) {
            service.getLongestSubHashByHeightRange(10, 1).block(Duration.ofSeconds(5))
        }
    }
}