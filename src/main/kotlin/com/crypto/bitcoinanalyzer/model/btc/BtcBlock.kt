package com.crypto.bitcoinanalyzer.model.btc

import com.crypto.bitcoinanalyzer.model.Block
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://btc.com/api-doc#Block">Structure</a>
 *
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
data class BtcBlock(
    override var height: Long,
    override val timestamp: Long,
    override val hash: String,
    override val size: Int,
    val version: Int,
    val bits: Int,
    val nonce: Long,
    val confirmations: Int,
    @JsonProperty("mrkl_root")
    val mrklRoot: String,
    @JsonProperty("curr_max_timestamp")
    val currMaxTimestamp: Long,
    @JsonProperty("prev_block_hash")
    val prevBlockHash: String?,
    @JsonProperty("next_block_hash")
    val nextBlockHash: String?,
    @JsonProperty("pool_difficulty")
    val poolDifficulty: Long,
    val difficulty: Long,
    @JsonProperty("tx_count")
    val txCount: Int,
    @JsonProperty("reward_block")
    val rewardBlock: Long,
    @JsonProperty("reward_fees")
    val rewardFees: Long,
    @JsonProperty("created_at")
    val createdAt: Long
) : Block(height, size, timestamp, hash)
