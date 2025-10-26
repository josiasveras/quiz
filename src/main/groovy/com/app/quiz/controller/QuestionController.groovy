package com.app.quiz.controller

import com.app.quiz.dto.QuestionRequest
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.service.QuestionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz/api")
class QuestionController {

    private final QuestionService questionService

    QuestionController(QuestionService questionService) {
        this.questionService = questionService
    }

    @GetMapping("/v1/questions/{id}")
    ResponseEntity<QuestionResponse> getQuestionById(@PathVariable("id") Long id) {
        def question = questionService.getQuestionById(id)
        return ResponseEntity.status(HttpStatus.OK).body(question)
    }

    @PostMapping("/v1/questions")
    ResponseEntity<QuestionResponse> submitQuestion(@Valid @RequestBody QuestionRequest questionRequest) {
        def questionSaved = questionService.saveQuestion(questionRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(questionSaved)

    }

}