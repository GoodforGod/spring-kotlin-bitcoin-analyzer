package com.arrival.crypto.bitcoinanalyzer.model.btc

import com.arrival.crypto.bitcoinanalyzer.model.Block
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://btc.com/api-doc#Block">Structure</a>
 *
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
data class BtcBlock(
    override var number: Long,
    override val timestamp: Int,
    override val hash: String,
    override val size: Int,
    val version: Int,
    val bits: Int,
    val nonce: Int,
    val confirmations: Int,
    @JsonProperty("mrkl_root")
    val mrklRoot: String,
    @JsonProperty("curr_max_timestamp")
    val currMaxTimestamp: Int,
    @JsonProperty("prev_block_hash")
    val prevBlockHash: String?,
    @JsonProperty("next_block_hash")
    val nextBlockHash: String?,
    @JsonProperty("pool_difficulty")
    val poolDifficulty: Int,
    val difficulty: Int,
    @JsonProperty("tx_count")
    val txCount: Int,
    @JsonProperty("reward_block")
    val rewardBlock: Int,
    @JsonProperty("reward_fees")
    val rewardFees: Int,
    @JsonProperty("created_at")
    val createdAt: Int
) : Block(number, size, timestamp, hash)
