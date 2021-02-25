package com.arrival.crypto.bitcoinanalyzer.model

/**
 * Abstract Blockchain Block
 *
 * @author Anton Kurako (GoodforGod)
 * @since 25.2.2021
 */
abstract class Block(
    open val number: Long,
    open val size: Int,
    open val timestamp: Int,
    open val hash: String): Comparable<Block> {

    open val hashDecoded: String by lazy { Integer.parseInt(hash, 16).toString() }

    override fun compareTo(other: Block): Int {
        return number.compareTo(other.number)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }

    override fun toString(): String {
        return "Block(number=$number, size=$size, timestamp=$timestamp, hash='$hash')"
    }
}
