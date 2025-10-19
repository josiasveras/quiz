package com.app.quiz.controller

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.model.Question
import com.app.quiz.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz")
class QuestionController {

    private final QuestionService questionService

    QuestionController(QuestionService questionService) {
        this.questionService = questionService
    }

    @GetMapping("/question/{id}")
    QuestionResponse getQuestionById(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id)
    }

    @PostMapping("/question")
    Question submitAnswer(@RequestBody Question question) {
        return questionService.saveQuestion(question)
    }

}