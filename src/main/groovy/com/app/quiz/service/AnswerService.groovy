package com.app.quiz.service

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.model.Answer
import com.app.quiz.repository.AnswerRepository
import com.app.quiz.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class AnswerService {

    private final QuestionRepository questionRepository
    private final AnswerRepository answerRepository

    AnswerService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository
        this.answerRepository = answerRepository
    }

    AnswerResponse validateAnswer(AnswerRequest answerRequest) {
        def questionOptional = questionRepository?.findById(answerRequest.questionId)
        if (questionOptional.isEmpty()) {
            throw new QuestionNotFoundException(answerRequest.questionId)
        }

        def question = questionOptional.get()

        boolean isCorrect = question.correctAnswer.equalsIgnoreCase(answerRequest.selectedAnswer?.trim())

        def answer = (
                new Answer(
                        question: question,
                        selectedAnswer: answerRequest.selectedAnswer,
                        correct: isCorrect
                )
        )

        saveAnswer(answer)

        return new AnswerResponse(
                correct: isCorrect,
                message: isCorrect ? "Resposta correta!" : "Resposta incorreta."
        )
    }

    Answer saveAnswer(Answer answer) {
        answerRepository.save(answer)
    }

}
