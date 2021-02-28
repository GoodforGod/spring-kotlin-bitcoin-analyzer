package com.arrival.crypto.bitcoinanalyzer.model

import java.math.BigInteger

/**
 * Abstract Blockchain Block
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
open class Block(
    open val height: Long,
    open val size: Int,
    open val timestamp: Int,
    open val hash: String
) : Comparable<Block> {

    open val hashDecoded: String by lazy {
        if (hash.isEmpty())
            return@lazy ""
        else
            BigInteger(hash, 16).toString()
    }

    override fun compareTo(other: Block): Int {
        return height.compareTo(other.height)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        return height.hashCode()
    }

    override fun toString(): String {
        return "Block(height=$height, size=$size, timestamp=$timestamp, hash='$hash')"
    }
}
