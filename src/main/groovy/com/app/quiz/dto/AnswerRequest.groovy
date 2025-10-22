package com.app.quiz.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AnswerRequest {

    @NotNull
    Long questionId

    @NotBlank
    String selectedAnswer

}
