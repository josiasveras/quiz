package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.service.AnswerService
import com.app.quiz.utils.TestUtils
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AnswerController)
class AnswerControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    AnswerService answerService

    def "should validate an answer and return correct answer response"() {
        given: "a correct mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "B) Harpa"
        )

        and: "a correct mock answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: true,
                message: "Resposta correta!"
        )

        and: "the service validates answer and returns the correct mock response"
        Mockito.when(answerService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        when: "the POST request is sent with Json body"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isCreated())
                .andExpect(jsonPath('$.correct').value(true))
                .andExpect(jsonPath('$.message').value("Resposta correta!"))
    }

    def "should validate an answer and return incorrect answer response"() {
        given: "an incorrect mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "C) Flauta"
        )

        and: "a incorrect mock answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: false,
                message: "Resposta incorreta."
        )

        and: "the service validates answer and returns the mock response"
        Mockito.when(answerService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        when: "the POST request is sent with Json body"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isCreated())
                .andExpect(jsonPath('$.correct').value(false))
                .andExpect(jsonPath('$.message').value("Resposta incorreta."))
    }

    def "should return not found answer id response"() {
        given: "a non-existent id in mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 99L,
                selectedAnswer: "B) Harpa"
        )

        and: "a not found mock answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: false,
                message: "Pergunta não encontrada."
        )

        and: "the service validates answer and returns not found mock answer response"
        Mockito.when(answerService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        when: "the POST request is sent with Json body with not found mock answer request"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isCreated())
                .andExpect(jsonPath('$.correct').value(false))
                .andExpect(jsonPath('$.message').value("Pergunta não encontrada."))
    }

}
