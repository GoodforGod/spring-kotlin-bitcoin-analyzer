package com.crypto.bitcoinanalyzer.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author GoodforGod
 * @since 27.02.2021
 */
class BlockHashDecodedTests : Assertions() {

    @Test
    fun `block hash decoded properly`() {
        val block = Block(9, 10, 10, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6");
        assertEquals("1056850286200300937094298967488490265669752758871228406", block.hashDecoded)
        assertNotNull(block.toString())
    }

    @Test
    fun `blocks with same height equal`() {
        val block1 = Block(1, 10, 10, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6");
        val block2 = Block(1, 10, 10, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6");
        assertEquals(block1, block2)
    }

    @Test
    fun `blocks not equal`() {
        val block1 = Block(1, 10, 10, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6");
        val block2 = Block(2, 10, 10, "0000000000000000000b08b64f625bf4c51d0a34ebaef2b7451c0840149f7ff6");
        assertNotEquals(block1, block2)
        assertEquals(1, block2.compareTo(block1))
    }
}