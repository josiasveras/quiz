package com.app.quiz.service

import com.app.quiz.dto.QuestionResponse
import org.springframework.stereotype.Service

@Service
class QuestionService {

    static QuestionResponse getRandomQuestion() {
        return new QuestionResponse(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"]
        )
    }
}
