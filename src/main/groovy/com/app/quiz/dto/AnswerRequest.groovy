package com.app.quiz.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AnswerRequest {

    @JsonProperty("questionId")
    Long questionId

    @JsonProperty("selectedAnswer")
    String selectedAnswer
}
