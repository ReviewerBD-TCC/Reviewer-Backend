package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "question_form")
@Table(name = "question_forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionForm{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionForm(Form form, Question question) {
        this.form = form;
        this.question = question;
    }
}