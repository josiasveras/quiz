package com.app.quiz.utils

import com.fasterxml.jackson.databind.ObjectMapper

class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()

    /**
     * Converts a Groovy/Java object to a JSON string.
     */
    static String writeValueAsString(Object object) {
        return objectMapper.writeValueAsString(object)
    }
}
