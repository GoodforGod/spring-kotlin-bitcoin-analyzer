package com.arrival.crypto.bitcoinanalyzer.client

import com.arrival.crypto.bitcoinanalyzer.error.BlockchainClientException
import com.arrival.crypto.bitcoinanalyzer.error.BlockchainParamException
import com.arrival.crypto.bitcoinanalyzer.model.btc.BtcBlock
import com.arrival.crypto.bitcoinanalyzer.model.btc.BtcMultiResponse
import com.arrival.crypto.bitcoinanalyzer.model.btc.BtcSingleResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * @see <a href="https://btc.com/api-doc">API</a>
 *
 * @author Anton Kurako (GoodforGod)
 * @since 24.2.2021
 */
@Component
class BtcClient {

    /**
     * Block height on 25.3.2021 is 672_117, so this limit will do just fine next 100 years
     */
    private val maxBlockHeight: Long = 9_999_999
    private val pathBlock: String = "/block/"
    private val client: WebClient = WebClient.create("https://chain.api.btc.com/v3")

    fun getBlocks(height: Long): Flux<BtcBlock> {
        if (height < 1 || height > maxBlockHeight)
            return Flux.empty()

        val type = object : ParameterizedTypeReference<BtcSingleResponse<BtcBlock>>() {}
        return client.get()
            .uri(pathBlock + height)
            .acceptCharset(Charsets.UTF_8)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(
                { it == HttpStatus.BAD_REQUEST },
                { throw BlockchainParamException("Malformed block height: $height") })
            .onStatus(
                { it != HttpStatus.OK },
                { throw BlockchainClientException("Unknown error occurred on client side for height: $height") })
            .bodyToFlux(type)
            .timeout(Duration.ofSeconds(5))
            .flatMap {
                if (it.data == null)
                    Flux.empty()
                else
                    Flux.just(it.data)
            }
    }

    fun getBlocks(heights: Collection<Long>): Flux<BtcBlock> {
        if (heights.isEmpty())
            return Flux.empty()

        val filtered = heights.asSequence().distinct()
            .filter { it in 1 until maxBlockHeight }
            .toSet()

        if (filtered.isEmpty())
            return Flux.empty()
        if (filtered.size == 1)
            return getBlocks(filtered.first())

        val path = filtered.joinToString(",", pathBlock)
        val type = object : ParameterizedTypeReference<BtcMultiResponse<BtcBlock>>() {}
        return client.get()
            .uri(path)
            .acceptCharset(Charsets.UTF_8)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(
                { it == HttpStatus.BAD_REQUEST },
                { throw BlockchainParamException("Malformed block heights: $heights") })
            .onStatus(
                { it != HttpStatus.OK },
                { throw BlockchainClientException("Unknown error occurred on client side for heights: $heights") })
            .bodyToFlux(type)
            .timeout(Duration.ofSeconds(10))
            .flatMapIterable { it.data ?: emptyList() }
    }
}