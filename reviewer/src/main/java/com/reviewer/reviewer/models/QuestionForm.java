package com.reviewer.reviewer.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;






@Entity(name = "question_form")
@Table(name = "questions_form")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  QuestionForm{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;



}