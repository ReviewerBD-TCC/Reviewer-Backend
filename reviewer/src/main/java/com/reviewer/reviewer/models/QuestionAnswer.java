package com.reviewer.reviewer.models;
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

public class QuestionAnswer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private FormQuestion formQuestion;

    private String answer;

}
