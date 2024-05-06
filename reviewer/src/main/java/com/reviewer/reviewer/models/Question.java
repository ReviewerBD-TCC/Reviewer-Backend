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
    @Column(length = 300)
    private String questionPt;
    @Column(length = 300)
    private String questionEn;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionForm> questionForms;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private QuestionAnswer answer;
    
    private Boolean active;

    public Question (QuestionDto data){
        this.questionEn = data.questionEn();
        this.questionPt = data.questionPt();
        this.active = data.active();
    }

}
