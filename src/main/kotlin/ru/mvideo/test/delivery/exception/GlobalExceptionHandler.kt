package ru.mvideo.test.delivery.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(val status: Int, val message: String?)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DeliveryNotFoundException::class)
    fun handleNotFound(ex: DeliveryNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message))

    @ExceptionHandler(InvalidDeliveryStatusTransitionException::class)
    fun handleInvalidTransition(ex: InvalidDeliveryStatusTransitionException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message))
}
