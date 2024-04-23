package com.reviewer.reviewer.dto.questions;
import java.util.List;

import com.reviewer.reviewer.models.QuestionAnswer;



public record QuestionAnswerResponseDto(Long id, Long userId, String name, String questionPt,String questionEn, List<QuestionResponseDto> questions, List<String> answers) {
    public QuestionAnswerResponseDto(QuestionAnswer data,List<QuestionResponseDto> questions, List<String> answers){
        this(data.getId(), data.getUser().getId(), data.getUser().getName(), data.getQuestionForm().getQuestion().getQuestionPt(),data.getQuestionForm().getQuestion().getQuestionPt(),questions, answers);
    };
    
}