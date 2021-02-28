package com.arrival.crypto.bitcoinanalyzer.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.2.2021
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BitcoinBlockControllerTests(@Autowired val restTemplate: TestRestTemplate) : Assertions() {

    @Test
    fun `valid sub hash is calculated`() {
        val entity = restTemplate.getForEntity<String>("/bitcoin/block/subhash?from=646940&to=646941")
        assertEquals(HttpStatus.OK, entity.statusCode)
        assertEquals("2989", entity.body)
    }

    @Test
    fun `from less than 0 is rejected`() {
        val entity = restTemplate.getForEntity<String>("/bitcoin/block/subhash?from=-1&to=5")
        assertEquals(HttpStatus.BAD_REQUEST, entity.statusCode)
    }

    @Test
    fun `to less than from is rejected`() {
        val entity = restTemplate.getForEntity<String>("/bitcoin/block/subhash?from=5&to=3")
        assertEquals(HttpStatus.BAD_REQUEST, entity.statusCode)
    }

    @Test
    fun `to more than integer is rejected`() {
        val entity = restTemplate.getForEntity<String>("/bitcoin/block/subhash?from=64694000000&to=6469410000000")
        assertEquals(HttpStatus.BAD_REQUEST, entity.statusCode)
    }

    @Test
    fun `range more than 100 is rejected`() {
        val entity = restTemplate.getForEntity<String>("/bitcoin/block/subhash?from=100&to=300")
        assertEquals(HttpStatus.BAD_REQUEST, entity.statusCode)
    }
}