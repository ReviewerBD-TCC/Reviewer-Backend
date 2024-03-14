package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.Indicando;

import java.util.List;

public record IndicandoResponseDto(
        Long id,
        Long indicado,
        List<IndicadosDto> indicados
) {
    public IndicandoResponseDto(Indicando form) {
        this(form.id(), form.indicando(), form.indicados());
    }
//    public FormIndicationResponseDto(FormIndication formIndicated, IndicatedUserDto indicated) {
//        this(formIndicated.getId(), formIndicated.getIndicatingUser().getId(), indicated);
//    }
}
