package com.app.quiz.controller

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.service.QuestionService
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(QuestionController)
class QuestionControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService

    def "should return a question with options by id"() {
        given: "a mock question response"
        def mockQuestions = new QuestionResponse(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"]
        )

        and: "the service returns the mock response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenReturn(mockQuestions);

        when: "the GET request is sent"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", 1L))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.id').value(1))
                .andExpect(jsonPath('$.text').value("Qual instrumento Davi gostava de tocar?"))
                .andExpect(jsonPath('$.options').isArray())
                .andExpect(jsonPath('$.options.length()').value(3))
    }

    def "should return 404 not found error for non-existent question id"() {
        given: "a valid type but non-existent question id"
        def id = 99L

        and: "the service returns the 404 error code response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID ${id} not found."))

        when: "the GET request is sent with a non-existent question id"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", id))

        then: "the endpoint responds with 404 error code JSON response"
        response.andExpect(status().isNotFound())

    }

    def "should return 400 bad request error for invalid question id"() {
        given: "an invalid question id"
        def id = "batata"

        when: "the GET request is sent with an invalid question id"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", id))

        then: "the endpoint responds with 404 error code JSON response"
        response.andExpect(status().isBadRequest())

    }

}