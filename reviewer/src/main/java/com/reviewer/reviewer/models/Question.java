package com.reviewer.reviewer.models;



import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "question")
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionForm> questionForms;

    private Boolean active;

    public Question(QuestionDto data){
        this.question = data.question();
        this.active = true;
    }

}
