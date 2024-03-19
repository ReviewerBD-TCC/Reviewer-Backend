package com.reviewer.reviewer.dto.forms;

import java.util.List;

public record FormIndicationDto(
        Long id,
        Long userIndication,
        List<IndicatedDto> indicados
) {
}
