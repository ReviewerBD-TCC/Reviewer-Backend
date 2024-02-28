package com.reviewer.reviewer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonIgnore
    private QuestionForm questionForm;
    private String answer;

    public QuestionAnswer(QuestionAnswerDto data) {
        this.answer = data.answer();
        this.questionForm = data.questionFormId();
        this.user = data.userId();
    }
}

