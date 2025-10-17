package com.app.quiz.controller

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.dto.QuestionResponse
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
        def response = this.mockMvc.perform(get("/quiz/question/{id}", 1L))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.id').value(1))
                .andExpect(jsonPath('$.text').value("Qual instrumento Davi gostava de tocar?"))
                .andExpect(jsonPath('$.options').isArray())
                .andExpect(jsonPath('$.options.length()').value(3))
    }

    def "should validate an answer and return answer response"() {
        given: "a mock answer response"
        def mockAnswerResponse = new AnswerResponse(
                correct: true,
                message: "Resposta correta!"
        )

        and: "a mock answer request"
        def mockAnswerRequest = new AnswerRequest(
                questionId: 1L,
                selectedAnswer: "B) Harpa"
        )

        and: "the service validates answer and returns the mock response"
        Mockito.when(questionService.validateAnswer(ArgumentMatchers.any())).thenReturn(mockAnswerResponse)

        when: "the POST request is sent with Json body"
        def response = this.mockMvc.perform(post("/quiz/answer").content(TestUtils.writeValueAsString(mockAnswerRequest)).contentType(MediaType.APPLICATION_JSON))

        then: "the endpoint responds with status 200 and valid JSON"
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.correct').value(true))
                .andExpect(jsonPath('$.message').value("Resposta correta!"))
    }
}