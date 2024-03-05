package com.reviewer.reviewer.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "question_answer")
@Table(name = "questions_answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  QuestionAnswer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionForm questionForm;
 
    private String answer;

    public QuestionAnswer(User user, QuestionForm questionForm, String answer){
        this.user = user;
        this.questionForm = questionForm;
        this.answer = answer;
    }

   
}

