package com.app.quiz.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class QuestionRequest {

    @NotBlank(message = "Batata assada com carne")
    String text

    @NotEmpty(message = "Question options must not be empty")
    @Size(min = 3, message = "Question must contain at least 3 options values")
    @Valid
    List<@NotBlank(message = "Options values must not be empty")  String> options = []

    @NotBlank(message = "Text must not be null")
    String correctAnswer
}
