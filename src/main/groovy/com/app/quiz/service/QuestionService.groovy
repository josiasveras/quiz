package com.app.quiz.service

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.model.Question
import com.app.quiz.repository.QuestionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuestionService {

    private final QuestionRepository questionRepository

    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository
    }

    QuestionResponse getQuestionById(Long id) {
        def question = questionRepository.findById(id)
                .orElseThrow { new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found with ID: $id") }

        return new QuestionResponse(
                id: question.id,
                text: question.text,
                options: question.options
        )
    }

    List<QuestionResponse> getAllQuestions() {
        return questionRepository.findAll().collect { q ->
            new QuestionResponse(id: q.id, text: q.text, options: q.options)
        }
    }

    Question saveQuestion(Question question) {
        return questionRepository.save(question)
    }

}
