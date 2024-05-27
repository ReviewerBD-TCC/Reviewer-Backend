package com.reviewer.reviewer.dto.forms;
import com.reviewer.reviewer.dto.questions.QuestionResponseDto;

import java.time.LocalDate;
import java.util.List;



public record QuestionFormListDto(Long id, List<IndicationFormResponseDto> indicated, String title, LocalDate year, List<QuestionResponseDto> questions) {
    
}
