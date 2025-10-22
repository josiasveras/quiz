package com.app.quiz.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Custom exception thrown when a question is not found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class QuestionNotFoundException extends RuntimeException {

    QuestionNotFoundException(Long id) {
        super("Question not found with ID: $id")
    }

}
