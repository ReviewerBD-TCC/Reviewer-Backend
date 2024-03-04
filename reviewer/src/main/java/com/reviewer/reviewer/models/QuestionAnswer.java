package com.reviewer.reviewer.models;

import java.util.List;
import java.util.Set;

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
    
    @OneToMany(mappedBy = "questionAnswer", cascade = CascadeType.ALL)
    private List<QuestionFormAnswer> questionFormAnswers;
    private String answer;

   
}

