package com.reviewer.reviewer.dto.forms;
import java.util.List;



public record QuestionFormListDto(String title, String year, List<Long>questionsId) {
    
}
