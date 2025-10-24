package com.app.quiz.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

/**
 * Global class for centralized error and exception handling.
 * All exceptions thrown in the application pass through this class.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException)
    ResponseEntity<Map<String, Object>> handleValidationsErrors(MethodArgumentNotValidException ex) {
        def errors = ex.bindingResult.allErrors.collectEntries { error ->
            [((error as FieldError).field): error.defaultMessage]
        }

        def body = [
                timestamp: new Date(),
                status   : HttpStatus.BAD_REQUEST.value(),
                error    : "Validation Error",
                message  : "Invalid input data",
                details  : errors
        ]
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(QuestionNotFoundException)
    ResponseEntity<Map<String, Object>> handleQuestionNotFound(QuestionNotFoundException ex) {
        def body = [
                timestamp: new Date(),
                status   : HttpStatus.NOT_FOUND.value(),
                error    : "Not Found",
                message  : ex.message
        ]
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body) as ResponseEntity<Map<String, Object>>
    }

    @ExceptionHandler(Exception)
    ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        def body = [
                timestamp: new Date(),
                status   : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error    : "Internal Server Error",
                message  : ex.message
        ]
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body) as ResponseEntity<Map<String, Object>>
    }

    @ExceptionHandler(NoResourceFoundException)
    ResponseEntity<Map<String, Object>> handleNoResourceFound(NoResourceFoundException ex) {
        def body = [
                timestamp: new Date(),
                status   : HttpStatus.NOT_FOUND.value(),
                error    : "No Resource Found Error",
                message  : ex.message
        ]
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body) as ResponseEntity<Map<String, Object>>
    }
}