package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.dto.users.RegisterResponseDto;
import com.reviewer.reviewer.models.Question;
import com.reviewer.reviewer.models.QuestionForm;
import com.reviewer.reviewer.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public record QuestionFormListDto(Long id, String title, LocalDate year, List<QuestionResponseDto> questions, RegisterResponseDto user) {

    public QuestionFormListDto(QuestionForm questionForm, RegisterResponseDto user) {
        this(questionForm.getId(), questionForm.getForm().getTitle(),questionForm.getForm().getYear(),convertToQuestionResponseDtos(questionForm.getForm().getQuestionForms()), user);
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

