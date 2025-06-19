package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz")
class QuestionController {

    private final QuestionService questionService

   QuestionController(QuestionService questionService) {
       this.questionService = questionService
   }

    @GetMapping("/question")
    QuestionResponse getQuestion() {
        return questionService.getRandomQuestion()
    }

    @PostMapping("/answer")
    AnswerResponse submitAnswer(@RequestBody AnswerRequest answerRequest) {
        return questionService.validateAnswer(answerRequest)
    }
}