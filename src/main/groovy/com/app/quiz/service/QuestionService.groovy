package com.app.quiz.service

import com.app.quiz.dto.QuestionRequest
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.exception.QuestionNotFoundException
import com.app.quiz.model.Question
import com.app.quiz.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService {

    private final QuestionRepository questionRepository

    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository
    }

    QuestionResponse getQuestionById(Long id) {
        def question = questionRepository.findById(id)
                .orElseThrow { new QuestionNotFoundException(id) }

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

    QuestionResponse saveQuestion(QuestionRequest questionRequest) {
        def question = new Question(
                text: questionRequest.text,
                options: questionRequest.options,
                correctAnswer: questionRequest.correctAnswer
        )

        questionRepository.save(question)

        return new QuestionResponse(
                id: question.id,
                text: question.text,
                options: question.options
        )
    }

}
