package com.reviewer.reviewer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long id;
    @OneToMany(mappedBy = "form_id", fetch = FetchType.LAZY)
    private Form form;
    @ManyToOne
    @JoinColumn(name = "question_answer_id")
    @JsonIgnore
    private QuestionAnswer questionAnswer;

}