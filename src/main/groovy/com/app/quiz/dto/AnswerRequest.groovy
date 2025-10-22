package com.app.quiz.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AnswerRequest {

    @NotNull(message = "Question ID must not be null")
    Long questionId

    @NotBlank(message = "Selected answer cannot be blank")
    String selectedAnswer

}
