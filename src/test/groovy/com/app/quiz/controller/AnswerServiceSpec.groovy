package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.model.Question
import com.app.quiz.repository.AnswerRepository
import com.app.quiz.repository.QuestionRepository
import com.app.quiz.service.AnswerService
import spock.lang.Specification

class AnswerServiceSpec extends Specification {

    def questionRepository = Mock(QuestionRepository)
    def answerRepository = Mock(AnswerRepository)
    def answerService = new AnswerService(questionRepository, answerRepository)

    def "should return correct response when answer is correct"() {
        given: "a mock question exists in the repository"
        def question = new Question(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"],
                correctAnswer: "B) Harpa"
        )

        questionRepository.findById(1L) >> Optional.of(question)

        and: "a correct mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "B) Harpa"
        )

        when: "the service validates answer and returns the correct mock response"
        def response = answerService.validateAnswer(mockAnswerRequest)

        then: "the service responds correct response"
        response instanceof AnswerResponse
        response.correct
        response.message == "Resposta correta!"

    }

    def "should return incorrect response when answer is incorrect"() {
        given: "a mock question exists in the repository"
        def question = new Question(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"],
                correctAnswer: "B) Harpa"
        )

        questionRepository.findById(1L) >> Optional.of(question)

        and: "a incorrect mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "C) Flauta"
        )

        when: "the service validates answer"
        def response = answerService.validateAnswer(mockAnswerRequest)

        then: "the service responds with incorrect response"
        response instanceof AnswerResponse
        !response.correct
        response.message == "Resposta incorreta."

    }

    def "should throw exception when question is not found"() {
        given: "repository returns empty result"
        questionRepository.findById(99L) >> Optional.empty()

        and: "a request with non-existent question id"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 99L,
                selectedAnswer: "C) Flauta"
        )

        when: "the service validates the answer"
        answerService.validateAnswer(mockAnswerRequest)

        then: "an exception should be thrown"
        thrown(QuestionNotFoundException)
    }

}
