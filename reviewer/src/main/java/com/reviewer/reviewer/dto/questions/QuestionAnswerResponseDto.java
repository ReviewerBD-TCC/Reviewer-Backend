package com.reviewer.reviewer.dto.questions;
import com.reviewer.reviewer.models.Dashboard;
import com.reviewer.reviewer.models.QuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public record QuestionAnswerResponseDto(
        Long id,
        Long questionFormId,
        String questionFormTitle,
        Integer quantityAnsweredForm,
        Integer quantityFormSent,
        String whoAnsweredId,
        String whoAnsweredName,
        QuestionResponseDto question,

        String answer,
        String forWhichUser,
        String whichUserName
) {
    public QuestionAnswerResponseDto(QuestionAnswer questionAnswer, QuestionResponseDto question, Dashboard dashboard) {
        this(questionAnswer.getId(), questionAnswer.getQuestionForm().getForm().getId(),questionAnswer.getQuestionForm().getForm().getTitle(), dashboard.getQuantityAnsweredFormSent(),dashboard.getFormQuantitySent(), questionAnswer.getUser().getId(), questionAnswer.getUser().getName(), question, questionAnswer.getAnswer(), questionAnswer.getForWhichUser().getId(), questionAnswer.getForWhichUser().getName());
    }

    public static List<QuestionAnswerResponseDto> fromQuestionAnswerList(List<QuestionAnswer> answerList, Dashboard dashboard){
        List<QuestionAnswerResponseDto> dtos = new ArrayList<>();

        for(QuestionAnswer answer : answerList){
            var question = new QuestionResponseDto(answer.getQuestion());
            dtos.add(new QuestionAnswerResponseDto(answer.getId(), answer.getQuestionForm().getForm().getId(),answer.getQuestionForm().getForm().getTitle(), dashboard.getQuantityAnsweredFormSent(),dashboard.getFormQuantitySent(), answer.getUser().getId(),answer.getUser().getName(), question, answer.getAnswer(), answer.getForWhichUser().getId(), answer.getForWhichUser().getName()));
        }

        return dtos;
    }
}