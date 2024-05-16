package com.reviewer.reviewer.dto.forms;

import java.util.List;

public record FormUpdateDto(String title, List<NewQuestionFormUpdateDto> newQuestion){

}