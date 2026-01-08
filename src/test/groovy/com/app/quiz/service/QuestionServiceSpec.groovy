package com.app.quiz.service

import com.app.quiz.dto.QuestionRequest
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.model.Question
import com.app.quiz.repository.QuestionRepository
import spock.lang.Specification

class QuestionServiceSpec extends Specification {

    def questionRepository = Mock(QuestionRepository)
    def questionService = new QuestionService(questionRepository)

    def "should return a question response by id"() {
        given: "a question mock"
        def questionMock = new Question(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"],
                correctAnswer: "B) Harpa"
        )

        and: "a valid database question id"
        def id = 1L

        questionRepository.findById(id) >> Optional.of(questionMock)

        when: "the service returns the question response"
        def response = questionService.getQuestionById(id)

        then: "the service responds with a valid question response"
        response instanceof QuestionResponse
        response.id == 1L
        response.text == "Qual instrumento Davi gostava de tocar?"
        response.options.size() == 3
    }

    def "should return exception for non-existent question id"() {
        given: "a non-existent database question id"
        def id = 99L

        questionRepository.findById(id) >> Optional.empty()

        when: "the service returns the question response"
        questionService.getQuestionById(id)

        then: "the service responds with a valid question response"
        thrown(QuestionNotFoundException)
    }

    def "should save a new question"() {
        given: "a mock question request"
        def mockQuestionRequest = new QuestionRequest(
                text: "Quem foi o 'assistente' do profeta Elias?",
                options: ["A) Eliseu", "B) Enoque", "C) Jonas"],
                correctAnswer: "A) Eliseu"
        )

        and: "a mock question"
        def mockQuestion = new Question(
                text: "Quem foi o 'assistente' do profeta Elias?",
                options: ["A) Eliseu", "B) Enoque", "C) Jonas"],
                correctAnswer: "A) Eliseu"
        )

        questionRepository.save(mockQuestion) >> mockQuestion

        when: "the service saves a question"
        def response = questionService.saveQuestion(mockQuestionRequest)

        then: "the service returns a valid question response"
        response.text == "Quem foi o 'assistente' do profeta Elias?"
        response.options.size() == 3
    }

    def "should return a question response list"() {
        given: "a question list mock"
        def mockQuestionResponseList = [
                new QuestionResponse(
                        id: 1L,
                        text: "Qual instrumento Davi gostava de tocar?",
                        options: ["A) Tambor", "B) Harpa", "C) Flauta"]
                ),
                new QuestionResponse(
                        id: 2L,
                        text: "Quando Jesus nasceu, onde Ele foi colocado?",
                        options: ["A) Em uma cama", "B) Em uma manjedoura", "C) Em um trono"]
                ),
                new QuestionResponse(
                        id: 3L,
                        text: "Na parábola do semeador, quais sementes que cresceram e deram uma boa colheita?",
                        options: ["A) As sementes que caíram nas pedras", "B) As sementes que caíram em boa terra", "C) As sementes que caíram entre os espinhos"]
                )]

        questionRepository.findAll() >> mockQuestionResponseList

        when: "the service returns the question response list"
        def response = questionService.getAllQuestions()

        then: "the service responds with a valid question response list"
        response instanceof ArrayList<QuestionResponse>
        !response.isEmpty()
    }
}