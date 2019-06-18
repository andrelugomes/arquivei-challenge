package br.com.arquivei.api.config.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ResourceAdvice {

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementHandler() : ResponseEntity<Details> {
       return ResponseEntity
           .status(HttpStatus.NOT_FOUND)
           .body(Details("Resource not found!"))
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: java.lang.Exception) : ResponseEntity<Details> {
        return ResponseEntity
           .status(HttpStatus.INTERNAL_SERVER_ERROR)
           .body(Details(exception.message))
    }
}

data class Details(var message: String?)
