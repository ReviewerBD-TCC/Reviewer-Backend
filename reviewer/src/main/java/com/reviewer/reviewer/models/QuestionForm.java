package com.reviewer.reviewer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import java.util.Set;
>>>>>>> origin/keven

@Entity(name = "question_form")
@Table(name = "questions_form")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  QuestionForm{

<<<<<<< HEAD
    private Long id;
    @OneToMany(mappedBy = "form_id", fetch = FetchType.LAZY)
    private Form form;
    @ManyToOne
    @JoinColumn(name = "question_answer_id")
    @JsonIgnore
    private QuestionAnswer questionAnswer;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question questions;
>>>>>>> origin/keven

}