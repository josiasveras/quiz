package com.app.quiz.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class QuestionRequest {

    @JsonProperty("text")
    @NotBlank(message = "Question message must not be empty")
    String text

    @JsonProperty("options")
    @NotEmpty(message = "Question options must not be empty")
    @Size(min = 3, message = "Question must contain at least 3 options values")
    @Valid
    List<@NotBlank(message = "Options values must not be empty") String> options = []

    @JsonProperty("correctAnswer")
    @NotBlank(message = "Text must not be null")
    String correctAnswer
}
