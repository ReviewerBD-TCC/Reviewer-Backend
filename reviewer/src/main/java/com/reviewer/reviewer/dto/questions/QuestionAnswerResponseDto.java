package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.QuestionAnswer;



public record QuestionAnswerResponseDto(Long id, Long userId, String name, String questionPt,String questionEn, String answer) {
    public QuestionAnswerResponseDto(QuestionAnswer data){
        this(data.getId(), data.getUser().getId(), data.getUser().getName(), data.getQuestionForm().getQuestion().getQuestionPt(),data.getQuestionForm().getQuestion().getQuestionPt(), data.getAnswer());
    }
    
}
