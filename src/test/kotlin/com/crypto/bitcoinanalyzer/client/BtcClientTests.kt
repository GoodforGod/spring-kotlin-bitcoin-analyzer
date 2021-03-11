package com.crypto.bitcoinanalyzer.client

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
class BtcClientTests(@Autowired val client: BtcClient) : Assertions() {

    @Test
    fun `get block by height`() {
        val blocks = client.getBlocks(3).collectList().block(Duration.ofSeconds(5))
        assertEquals(1, blocks.size)
        val first = blocks.first()
        assertEquals(3, first.height)
        assertEquals(215, first.size)
        assertEquals("0000000082b5015589a3fdf2d4baff403e6f0be035a5d9742c1cae6295464449", first.hash)
    }

    @Test
    fun `get block by height less 0`() {
        val blocks = client.getBlocks(-1).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by empty heights`() {
        val blocks = client.getBlocks(listOf()).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by height more than limit`() {
        val blocks = client.getBlocks(99_999_999).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get blocks by multiple heights more than limit`() {
        val blocks = client.getBlocks(listOf(99_999_999L, 89_999_999L)).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get blocks by duplicate heights`() {
        val blocks = client.getBlocks(listOf(999L, 999L, 999L)).collectList().block(Duration.ofSeconds(5))
        assertEquals(1, blocks.size)
    }

    @Test
    fun `get block by not existing height`() {
        val heights = listOf(900_000L);
        val blocks = client.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertTrue(blocks.isEmpty())
    }

    @Test
    fun `get block by heights collection of size 1`() {
        val heights = listOf(3L);
        val blocks = client.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
        assertEquals(1, blocks.size)
        val first = blocks[0]
        assertEquals(3, first.height)
        assertEquals(215, first.size)
        assertEquals("0000000082b5015589a3fdf2d4baff403e6f0be035a5d9742c1cae6295464449", first.hash)
    }

    @Test
    fun `get blocks by heights collection`() {
        val heights = listOf(2L, 3L);
        val blocks = client.getBlocks(heights).collectList().block(Duration.ofSeconds(5))
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