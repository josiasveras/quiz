package com.app.quiz.controller

import com.app.quiz.model.Question
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quiz")
class QuestionController {

    @GetMapping("/question")
    Question getQuestion() {
        return new Question(
                statement: "Qual o primeiro livro da Bíblia?",
                options: ["Apocalipse", "Marcos", "Gênesis"]
        )
    }
}
