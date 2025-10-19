package com.app.quiz.repository

import com.app.quiz.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository extends JpaRepository<Answer, Long> {
}