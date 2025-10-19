package com.app.quiz.service

import com.app.quiz.dto.QuestionResponse
import com.app.quiz.model.Question
import com.app.quiz.repository.QuestionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuestionService {

    /*private final List<Question> mockQuestions = [
            new Question(
                    id: 1L,
                    text: "Qual instrumento Davi gostava de tocar?",
                    options: ["A) Tambor", "B) Harpa", "C) Flauta"],
                    correctAnswer: "B) Harpa"
            ),
            new Question(
                    id: 2L,
                    text: "Quando Jesus nasceu, onde Ele foi colocado?",
                    options: ["A) Em uma cama", "B) Em uma manjedoura", "C) Em um trono"],
                    correctAnswer: "B) Em uma manjedoura"
            ),
            new Question(
                    id: 3L,
                    text: "Na parábola do semeador, quais sementes que cresceram e deram uma boa colheita?",
                    options: ["A) As sementes que caíram nas pedras", "B) As sementes que caíram em boa terra", "C) As sementes que caíram entre os espinhos"],
                    correctAnswer: "B) As sementes que caíram em boa terra"
            ),
            new Question(
                    id: 4L,
                    text: "Quem são conhecidos como os patriarcas na Bíblia?",
                    options: ["A) Jesus, Maria e José", "B) Pedro, João e Tiago", "C) Abraão, Isaque e Jacó"],
                    correctAnswer: "C) Abraão, Isaque e Jacó"
            ),
            new Question(
                    id: 5L,
                    text: "Quantos livros há na Bíblia?",
                    options: ["A) 66", "B) 73", "C) 81"],
                    correctAnswer: "A) 66"
            )
    ]*/

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
            new QuestionResponse (id: q.id, text: q.text, options: q.options)
        }
    }

    Question saveQuestion(Question question) {
        return questionRepository.save(question)
    }

}
