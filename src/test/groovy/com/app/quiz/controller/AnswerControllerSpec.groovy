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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(AnswerController)
class AnswerControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    AnswerService answerService

    def "should validate an answer and return correct answer response"() {
        given: "a correct answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "B) Harpa"
        )

        and: "a correct answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: true,
                message: "Resposta correta!"
        )

        when: "the service validates answer and returns the correct answer response"
        Mockito.when(answerService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        and: "the post request is sent with answer request Json body"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 201 created and valid JSON"
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.correct').value(true))
                .andExpect(jsonPath('$.message').value("Resposta correta!"))
    }

    def "should validate an answer and return incorrect answer response"() {
        given: "an answer request with an incorrect answer"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "C) Flauta"
        )

        and: "an incorrect answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: false,
                message: "Resposta incorreta."
        )

        when: "the service validates answer and returns the answer response"
        Mockito.when(answerService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        and: "the post request is sent with Json body"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 201 created and valid JSON"
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.correct').value(false))
                .andExpect(jsonPath('$.message').value("Resposta incorreta."))
    }

    def "should return 400 bad request error for invalid answer request"() {
        given: "an invalid answer request"
        def mockAnswerRequest = new AnswerRequest(
                selectedAnswer: ""
        )

        when: "the post request is sent with Json body"
        def response = this.mockMvc.perform(post("/quiz/api/v1/answers").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 400 bad request and valid error JSON"
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    }

}
