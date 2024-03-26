package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity(name = "form_question")
@Table(name = "form_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormQuestion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public FormQuestion(Form form, Question question) {
        this.form = form;
        this.question = question;
    }
}