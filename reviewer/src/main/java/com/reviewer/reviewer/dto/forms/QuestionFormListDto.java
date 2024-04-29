package com.reviewer.reviewer.dto.forms;
import java.time.LocalDate;
import java.util.List;



public record QuestionFormListDto(String title, int year, List<Long>questionsId) {
    
}
