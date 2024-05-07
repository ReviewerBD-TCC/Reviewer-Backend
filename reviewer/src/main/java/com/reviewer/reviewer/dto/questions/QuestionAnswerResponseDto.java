package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public record QuestionAnswerResponseDto(
        Long id,
        Long questionFormId,
        Long userId,
        QuestionResponseDto question,
        String answer
) {
    public QuestionAnswerResponseDto(QuestionAnswer questionAnswer, QuestionResponseDto question) {
        this(questionAnswer.getId(), questionAnswer.getQuestionForm().getId(), questionAnswer.getUser().getId(), question, questionAnswer.getAnswer());
    }

    public static List<QuestionAnswerResponseDto> fromQuestionAnswerList(List<QuestionAnswer> answerList){
        List<QuestionAnswerResponseDto> dtos = new ArrayList<>();

        for(QuestionAnswer answer : answerList){
            var question = new QuestionResponseDto(answer.getQuestion());
            dtos.add(new QuestionAnswerResponseDto(answer.getId(), answer.getQuestionForm().getId(), answer.getUser().getId(), question, answer.getAnswer()));
        }

        return dtos;
    }
}
