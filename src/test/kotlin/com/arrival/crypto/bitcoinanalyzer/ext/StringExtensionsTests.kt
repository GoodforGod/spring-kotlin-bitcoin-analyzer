package com.arrival.crypto.bitcoinanalyzer.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author GoodforGod
 * @since 27.02.2021
 */
class StringExtensionsTests : Assertions() {

    @Test
    fun `substring found when exist`() {
        val longestSubstring = "1056850286200300937094298967488490265669752758871228406"
            .longestSubstring("1417115074586831187112255580375243348964882211567298911")

        assertEquals("2989", longestSubstring)
    }

    @Test
    fun `substring not found when not exist`() {
        val longestSubstring = "12345"
            .longestSubstring("6789")
        assertTrue(longestSubstring.isEmpty())
    }
}