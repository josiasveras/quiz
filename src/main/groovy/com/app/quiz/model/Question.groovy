package com.app.quiz.model

import jakarta.persistence.*

@Entity
@Table(name = "questions")

class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(nullable = false)
    String text

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option")
    List<String> options = []

    @Column(name = "correct_answer", nullable = false)
    String correctAnswer
}