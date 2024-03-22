package com.reviewer.reviewer.dto.forms;
import java.util.List;



public record QuestionFormListDto( Long formId, List<Long>questionsId) {
    
}
