package com.arrival.crypto.bitcoinanalyzer.model

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
    }
}