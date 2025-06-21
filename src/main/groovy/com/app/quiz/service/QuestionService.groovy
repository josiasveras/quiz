package com.app.quiz.service

import com.app.quiz.dto.AnswerRequest
import com.app.quiz.dto.AnswerResponse
import com.app.quiz.dto.QuestionResponse
import com.app.quiz.model.Question
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuestionService {

    private final List<Question> mockQuestions = [
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
    ]

    QuestionResponse getQuestionById(Long id) {
        def question = mockQuestions.find{ it.id == id}

        if (!question) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with ID ${id} not found.")
        }

        return new QuestionResponse(
                id: question.id,
                text: question.text,
                options: question.options
        )
    }

    AnswerResponse validateAnswer(AnswerRequest answerRequest) {
        def question = mockQuestions.find { it.id == answerRequest.questionId }

        if (!question) {
            return new AnswerResponse(
                    correct: false,
                    message: "Pergunta não encontrada."
            )
        }

        boolean isCorrect = question.correctAnswer.equalsIgnoreCase(answerRequest.selectedAnswer?.trim())

        return new AnswerResponse(
                correct: isCorrect,
                message: isCorrect ? "Resposta correta!" : "Resposta incorreta."
        )
    }
}
