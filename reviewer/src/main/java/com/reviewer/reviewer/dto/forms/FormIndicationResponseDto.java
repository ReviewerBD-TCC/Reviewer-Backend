package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.User;

import java.util.List;

public record FormIndicationResponseDto(
        Long id,
        Long userIndication,
        List<IndicatedResponseDto> indicados
) {

    public FormIndicationResponseDto(FormIndication lastIndication, User user, List<IndicatedResponseDto> indicadosResponseDto) {
        this(lastIndication.getId(), lastIndication.getUserIndication().getId(), indicadosResponseDto);
    }
}
