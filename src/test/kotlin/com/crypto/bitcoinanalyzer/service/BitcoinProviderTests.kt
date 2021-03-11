package com.crypto.bitcoinanalyzer.service

import com.crypto.bitcoinanalyzer.client.BtcClient
import com.crypto.bitcoinanalyzer.service.impl.BitcoinProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Duration

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
class BitcoinProviderTests : Assertions() {

    private val provider: BitcoinProvider = BitcoinProvider(BtcClient())

    @Test
    fun `get block by height less 0`() {
        val heights = listOf(-1L);
        val blocks = provider.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by height more than limit`() {
        val heights = listOf(99_999_999L);
        val blocks = provider.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by not existing height`() {
        val heights = listOf(900_000L);
        val blocks = provider.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by heights collection of size 1`() {
        val heights = listOf(3L);
        val blocks = provider.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertEquals(1, blocks.size)
        val first = blocks[0]
        assertEquals(3, first.height)
        assertEquals(215, first.size)
        assertEquals("0000000082b5015589a3fdf2d4baff403e6f0be035a5d9742c1cae6295464449", first.hash)
    }

    @Test
    fun `get blocks by heights collection`() {
        val heights = listOf(2L, 3L);
        val blocks = provider.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertEquals(2, blocks.size)

        val first = blocks[0]
        assertEquals(2, first.height)
        assertEquals(215, first.size)
        assertEquals("000000006a625f06636b8bb6ac7b960a8d03705d1ace08b1a19da3fdcc99ddbd", first.hash)

        val second = blocks[1]
        assertEquals(3, second.height)
        assertEquals(215, second.size)
        assertEquals("0000000082b5015589a3fdf2d4baff403e6f0be035a5d9742c1cae6295464449", second.hash)
    }
}