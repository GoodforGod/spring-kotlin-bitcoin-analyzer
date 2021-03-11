package com.crypto.bitcoinanalyzer.service.impl

import org.springframework.stereotype.Component

/**
 * @author GoodforGod
 * @since 11.03.2021
 */
@Component
class SubstringService {

    // N - list length
    // M - avg string length
    // complexity - N * M
    fun longestSubstring(list: List<String>): String {
        var res = ""
        for (hashIndex1 in list.indices) {
            val str1 = list[hashIndex1]
            val strLen = str1.length
            if (strLen < res.length)
                break

            for (i in 0 until strLen - res.length) {
                for (j in i + res.length + 1 until strLen) {
                    val substring = str1.substring(i, j)

                    val prev = res
                    for (hashIndex2 in list.indices) {
                        if (hashIndex1 == hashIndex2)
                            continue

                        val str2 = list[hashIndex2]
                        if (str2.contains(substring)) {
                            res = substring
                            break
                        }
                    }

                    if (res == prev)
                        break
                }
            }
        }

        return res
    }
}