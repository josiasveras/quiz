package com.app.quiz.controller

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(QuestionController)
class QuestionControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    QuestionService questionService

    def "should return a question with options" () {
        given: "a mock question response"
        def mockResponse = new QuestionResponse(
                id: 1L,
                text: "Qual instrumento Davi gostava de tocar?",
                options: ["A) Tambor", "B) Harpa", "C) Flauta"]
        )

        and: "the service returns the mock response"
        questionService.getRandomQuestion() >> mockResponse

        expect: "the endpoint responds with status 200 and valid JSON"
        mockMvc.perform (get("/quiz/question"))
                .andExpect (status().isOk())
                .andExpect (jsonPath('$.text').value("Qual instrumento Davi gostava de tocar?"))
                .andExpect (jsonPath('$.options').isArray())
                .andExpect (jsonPath('$.options.length()').value(3))
    }
}
