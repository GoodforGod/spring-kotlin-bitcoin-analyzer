package com.arrival.crypto.bitcoinanalyzer.controller

import com.arrival.crypto.bitcoinanalyzer.model.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import java.util.concurrent.TimeoutException

/**
 * @author GoodforGod
 * @since 28.02.2021
 */
@ControllerAdvice
class AnalyzerErrorController {

    @ExceptionHandler
    fun handleResponseExceptions(e: ResponseStatusException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.status)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse(e.status.value(), e.reason))
    }

    @ExceptionHandler
    fun handleTimeoutExceptions(e: TimeoutException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.message))
    }
}