package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.Indicated;

import java.util.ArrayList;
import java.util.List;

public record IndicatedResponseDto(
        Long id,
        String name
) {

    public IndicatedResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<IndicatedResponseDto> fromIndicatedList(List<Indicated> indicatedsList) {
        List<IndicatedResponseDto> dtos = new ArrayList<>();
        for (Indicated indicated : indicatedsList) {
            dtos.add(new IndicatedResponseDto(indicated.getId(), indicated.getUserIndicated().getName()));
        }
        return dtos;
    }
}
