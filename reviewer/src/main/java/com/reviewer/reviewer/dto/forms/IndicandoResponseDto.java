package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.Indicando;
import com.reviewer.reviewer.models.User;

import java.util.List;

public record IndicandoResponseDto(
        Long id,
        Long indicando,
        List<IndicadosResponseDto> indicados
) {
//    public IndicandoResponseDto(Indicando form, User usuario, List<Indicados> indicados) {
//        this(form.getId(), usuario.getId(), indicados);
//    }

    public IndicandoResponseDto(Indicando ultimoIndicando, User user, List<IndicadosResponseDto> indicadosResponseDto) {
        this(ultimoIndicando.getId(), ultimoIndicando.getUsuario().getId(), indicadosResponseDto);
    }
}
