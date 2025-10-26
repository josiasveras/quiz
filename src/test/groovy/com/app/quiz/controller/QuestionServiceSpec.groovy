package com.app.quiz.controller

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.service.QuestionService
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(QuestionController)
class QuestionServiceSpec extends Specification {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService

    def "should return 200 ok code and a question response by id"() {
        given: "a mock question response"
        def mockQuestions = new QuestionResponse(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"]
        )

        and: "a valid database question id"
        def id = 1L

        and: "the service returns the mock response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenReturn(mockQuestions);

        when: "the get request is sent with valid id"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", id))

        then: "the endpoint responds with status 200 and valid json"
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

        and: "the service returns the 404 error code response"
        Mockito.when(questionService.getQuestionById(ArgumentMatchers.any())).thenThrow(new QuestionNotFoundException(id))

        when: "the get request is sent with a non-existent question id"
        def response = this.mockMvc.perform(get("/quiz/api/v1/questions/{d}", id))

        then: "the endpoint responds with 404 error code JSON response"
        response.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    }

}