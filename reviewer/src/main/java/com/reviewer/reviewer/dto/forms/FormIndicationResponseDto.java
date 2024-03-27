package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.User;

import java.util.List;

public record FormIndicationResponseDto(
        Long id,
        Long userIndication,
        List<IndicatedResponseDto> indicateds
) {

    public FormIndicationResponseDto(IndicationForm lastIndication, User user, List<IndicatedResponseDto> indicadosResponseDto) {
        this(lastIndication.getId(), lastIndication.getUserIndication().getId(), indicadosResponseDto);
    }
}
