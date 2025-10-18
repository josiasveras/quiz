package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.service.AnswerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quiz")
class AnswerController {

    private final AnswerService answerService

    AnswerController(AnswerService answerService) {
        this.answerService = answerService
    }

    @PostMapping("/answer")
    AnswerResponse submitAnswer(@RequestBody AnswerRequest answerRequest) {
        return answerService.validateAnswer(answerRequest)
    }

}
