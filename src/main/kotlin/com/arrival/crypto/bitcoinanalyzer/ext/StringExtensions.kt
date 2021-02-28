package com.arrival.crypto.bitcoinanalyzer.ext

/**
 * @author GoodforGod
 * @since 27.02.2021
 */
fun String.longestSubstring(s: String): String {
    return longestSubstringOrNull(s).orEmpty()
}

fun String.longestSubstringOrNull(s: String): String? {
    var hash: String? = null
    for (i in indices) {
        val c1 = get(i)
        for (j in s.indices) {
            val c2 = s[j]
            if (c1 == c2) {

            }
        }
    }

    return null
}