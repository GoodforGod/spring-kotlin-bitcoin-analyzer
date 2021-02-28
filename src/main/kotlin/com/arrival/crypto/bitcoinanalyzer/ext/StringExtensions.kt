package com.arrival.crypto.bitcoinanalyzer.ext

/**
 * @author GoodforGod
 * @since 27.02.2021
 */
fun String.longestSubstring(s: String): String {
    val dp: Array<Array<Int>> = Array(this.length) { Array(s.length) { 0 } }
    var max = 0
    var end = -1
    for (i in indices) {
        val c1 = this[i]
        for (j in s.indices) {
            val c2 = s[j]
            if (c1 == c2) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                }

                if (max < dp[i][j]) {
                    max = dp[i][j]
                    end = i
                }
            }
        }
    }

    return this.substring(end - max + 1, end + 1)
}