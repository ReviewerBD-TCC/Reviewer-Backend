package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.Indicados;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record IndicadosResponseDto(
        Long id,
        String nome
) {

    public IndicadosResponseDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<IndicadosResponseDto> fromIndicadosList(List<Indicados> indicadosList) {
        List<IndicadosResponseDto> dtos = new ArrayList<>();
        for (Indicados indicado : indicadosList) {
            dtos.add(new IndicadosResponseDto(indicado.getId(), indicado.getUsuario().getName()));
        }
        return dtos;
    }
}
