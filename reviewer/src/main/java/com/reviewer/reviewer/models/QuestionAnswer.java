package com.reviewer.reviewer.models;
import com.reviewer.reviewer.dto.questions.QuestionAndAnswerDto;
import com.reviewer.reviewer.dto.questions.QuestionAnswerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity(name = "question_answer")
@Table(name = "questions_answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class QuestionAnswer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "who_answered_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "for_which_user_id")
    private User forWhichUser;

    @ManyToOne
    @JoinColumn(name = "questionForm_id")
    private QuestionForm questionForm;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    public QuestionAnswer(QuestionAndAnswerDto data, User user, Question question, QuestionForm questionForm, User forWhichUser) {
        this.user = user;
        this.questionForm = questionForm;
        this.question = question;
        this.answer = data.answer().answer();
        this.forWhichUser = forWhichUser;
    }
}
