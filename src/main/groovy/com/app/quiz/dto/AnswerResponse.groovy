package com.app.quiz.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AnswerResponse {

    @JsonProperty("correct")
    boolean correct

    @JsonProperty("message")
    String message
}
