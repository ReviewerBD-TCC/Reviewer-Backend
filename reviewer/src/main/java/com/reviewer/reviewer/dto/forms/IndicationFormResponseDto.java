package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.User;

import java.util.List;

public record IndicationFormResponseDto(
        Long id,
        Long userIndication,
        List<IndicatedResponseDto> indicateds
) {

    public IndicationFormResponseDto(IndicationForm lastIndication, User user, List<IndicatedResponseDto> indicatedResponseDto) {
        this(lastIndication.getId(), lastIndication.getUserIndication().getId(), indicatedResponseDto);
    }
}
