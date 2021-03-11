package com.crypto.bitcoinanalyzer.ext

/**
 * @author GoodforGod
 * @since 28.02.2021
 */
fun List<String>.longestSubstring(): String {
    if (isEmpty() || size == 1)
        return ""

    val n: Int = size
    val s: String = get(0)
    val len = s.length
    var res = ""
    for (i in 0 until len) {
        for (j in i + 1..len) {
            val stem = s.substring(i, j)
            var k = 1
            while (k < n) {
                // Check if the generated stem is common to all words
                if (get(k).contains(stem))
                    break
                k++
            }

            // If current substring is present in all strings and its length is greater than current result
            if (k == n && res.length < stem.length) res = stem
        }
    }

    return res
}
