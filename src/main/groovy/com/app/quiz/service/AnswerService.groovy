package com.app.quiz.service

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class AnswerService {

    private final QuestionRepository questionRepository

    AnswerService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository
    }

    AnswerResponse validateAnswer(AnswerRequest answerRequest) {
        def questionOptional = questionRepository?.findById(answerRequest.questionId)
        if (questionOptional.isEmpty()) {
            return new AnswerResponse(
                    correct: false,
                    message: "Pergunta não encontrada para o ID: $answerRequest.questionId."
            )
        }

        def question = questionOptional.get()

        boolean isCorrect = question.correctAnswer.equalsIgnoreCase(answerRequest.selectedAnswer?.trim())

        return new AnswerResponse(
                correct: isCorrect,
                message: isCorrect ? "Resposta correta!" : "Resposta incorreta."
        )
    }

}
