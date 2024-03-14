package com.reviewer.reviewer.dto.forms;

import java.util.List;

public record IndicandoDto(
        Long id,
        Long indicando,
        List<IndicadosDto> indicados
) {
//    public FormIndicationDto(FormIndicationResponseDto formIndicated) {
//        this(formIndicated.id(), formIndicated.indicatingUser(), formIndicated.indicatedUsers());
//    }
}
