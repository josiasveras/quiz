package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.service.AnswerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quiz/api")
class AnswerController {

    private final AnswerService answerService

    AnswerController(AnswerService answerService) {
        this.answerService = answerService
    }

    @PostMapping("/v1/answers")
    ResponseEntity<AnswerResponse> submitAnswer(@Valid @RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.validateAnswer(answerRequest))
    }

}
