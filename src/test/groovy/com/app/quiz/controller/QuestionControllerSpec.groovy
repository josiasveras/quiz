package com.app.quiz.controller

import com.app.quiz.dto.QuestionRequest
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.service.QuestionService
import com.app.quiz.utils.TestUtils
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(QuestionController)
class QuestionControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService

    def "should return a question with options by id"() {
        given: "a question response"
        def mockQuestions = new QuestionResponse(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"]
        )

        when: "the service returns the question response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenReturn(mockQuestions);

        and: "the get request is sent"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", 1L))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$.id').value(1))
                .andExpect(jsonPath('$.text').value("Qual instrumento Davi gostava de tocar?"))
                .andExpect(jsonPath('$.options').isArray())
                .andExpect(jsonPath('$.options.length()').value(3))
    }

    def "should return 404 not found error for non-existent question id"() {
        given: "a valid type but non-existent question id"
        def id = 99L

        when: "the service returns the 404 error code response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenThrow(new QuestionNotFoundException(id))

        and: "the get request is sent with a non-existent question id"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", id))

        then: "the endpoint responds with 404 not found error code JSON response"
        response.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    }

    def "should return 404 not found error for non-existent url"() {
        given: "a valid question id"
        def id = 1L

        and: "a non-existent url"
        def nonExistentUrl = "/quiz/api/v1/batata/{id}"

        when: "the get request is sent with a non-existent url"
        def response = this.mockMvc.perform(get("$nonExistentUrl", id))

        then: "the endpoint responds with 404 not found error code JSON response"
        response.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    }

    def "should save a new question"() {
        given: "a valid question request object"
        def questionRequestMock = new QuestionRequest(
                text: "Quem ensinou os discípulos a oração do 'Pai nosso'?",
                options: ["A) Os fariseus", "B) Jesus", "C) Paulo"],
                correctAnswer: "B) Jesus"
        )

        and: "a valid question response object"
        def questionResponseMock = new QuestionResponse(
                id: 37L,
                text: "Quem ensinou os discípulos a oração do 'Pai nosso'?",
                options: ["A) Os fariseus", "B) Jesus", "C) Paulo"]
        )

        when: "question service returns a question response"
        Mockito.when(questionService.saveQuestion(ArgumentMatchers.any())).thenReturn(questionResponseMock)

        and: "the post request is sent with valid json"
        def response = this.mockMvc.perform(post("/quiz/api/v1/questions").content(TestUtils.writeValueAsString(questionRequestMock)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with 200 ok code and valid json response"
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    def "should return 400 bad request error for invalid question request"() {
        given: "an invalid question request"
        def questionRequestMock = new QuestionRequest(
                text: "",
                options: ["", "", ""],
                correctAnswer: ""
        )

        when: "the post request is sent with invalid question request json"
        def response = this.mockMvc.perform(post("/quiz/api/v1/questions").content(TestUtils.writeValueAsString(questionRequestMock)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with 400 bad request error"
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

}