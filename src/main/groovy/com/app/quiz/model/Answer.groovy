package com.app.quiz.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "answers")
class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    Question question

    @Column(name = "selected_answer", nullable = false)
    String selectedAnswer

    @Column(name = "correct", nullable = false)
    Boolean correct = false

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt

    @PrePersist
    void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now()
        }
    }

}
