package com.reviewer.reviewer.dto.forms;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionForm;


public record QuestionFormResponseDto(Long id, IndicationForm indicated, String title, LocalDate year, List<QuestionResponseDto> questions) {

    public QuestionFormResponseDto(QuestionForm data){
        this(data.getForm().getId(), data.getIndication(), data.getForm().getTitle(), data.getForm().getYear(), convertToQuestionResponseDtos(data.getForm().getQuestionForms()));
    }

    private static List<QuestionResponseDto> convertToQuestionResponseDtos(List<QuestionForm> questionForms) {
        List<QuestionResponseDto> questions = new ArrayList<>();

        for (QuestionForm form : questionForms) {
            var question = form.getQuestion();
            questions.add(new QuestionResponseDto(question));
        }

        return questions;
    }
}