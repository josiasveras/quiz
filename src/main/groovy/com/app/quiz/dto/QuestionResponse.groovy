package com.app.quiz.dto

import com.fasterxml.jackson.annotation.JsonProperty

class QuestionResponse {

    @JsonProperty("id")
    Long id

    @JsonProperty("text")
    String text

    @JsonProperty("options")
    List<String> options = []
}
