package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public record QuestionAnswerResponseDto(
        Long id,
        Long questionFormId,
        String whoAnsweredId,
        String whoAnsweredName,
        QuestionResponseDto question,

        String answer,
        String forWhichUser,
        String whichUserName
) {
    public QuestionAnswerResponseDto(QuestionAnswer questionAnswer, QuestionResponseDto question) {
        this(questionAnswer.getId(), questionAnswer.getQuestionForm().getForm().getId(), questionAnswer.getUser().getId(), questionAnswer.getUser().getName(), question, questionAnswer.getAnswer(), questionAnswer.getForWhichUser().getId(), questionAnswer.getForWhichUser().getName());
    }

    public static List<QuestionAnswerResponseDto> fromQuestionAnswerList(List<QuestionAnswer> answerList){
        List<QuestionAnswerResponseDto> dtos = new ArrayList<>();

        for(QuestionAnswer answer : answerList){
            var question = new QuestionResponseDto(answer.getQuestion());
            dtos.add(new QuestionAnswerResponseDto(answer.getId(), answer.getQuestionForm().getForm().getId(), answer.getUser().getId(),answer.getUser().getName(), question, answer.getAnswer(), answer.getForWhichUser().getId(), answer.getForWhichUser().getName()));
        }

        return dtos;
    }
}