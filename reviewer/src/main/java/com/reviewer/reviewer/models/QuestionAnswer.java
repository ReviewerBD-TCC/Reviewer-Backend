package com.reviewer.reviewer.models;

<<<<<<< HEAD
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import jakarta.persistence.*;
>>>>>>> origin/keven
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
<<<<<<< HEAD

=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> origin/keven
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

