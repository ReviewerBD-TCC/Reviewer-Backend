package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;
import com.reviewer.reviewer.dto.users.UserDto;
import com.reviewer.reviewer.models.QuestionForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public record QuestionFormListDto(Long id, String title, LocalDate year, List<QuestionResponseDto> questions, UserDto user) {

    public QuestionFormListDto(QuestionForm questionForm, UserDto user) {
        this(questionForm.getForm().getId(), questionForm.getForm().getTitle(),questionForm.getForm().getYear(),convertToQuestionResponseDtos(questionForm.getForm().getQuestionForms()), user);
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

