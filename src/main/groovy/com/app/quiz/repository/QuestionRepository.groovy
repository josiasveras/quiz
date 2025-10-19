package com.app.quiz.repository

import com.app.quiz.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository extends JpaRepository<Question, Long> {
}
