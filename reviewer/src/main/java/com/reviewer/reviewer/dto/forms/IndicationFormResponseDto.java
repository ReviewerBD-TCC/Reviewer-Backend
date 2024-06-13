package com.reviewer.reviewer.dto.forms;

import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.User;

import java.util.ArrayList;
import java.util.List;

public record IndicationFormResponseDto(
        Long id,
        String userIndication,
        List<IndicatedResponseDto> indicateds
) {

    public IndicationFormResponseDto(IndicationForm lastIndication, List<IndicatedResponseDto> indicatedResponseDto) {
        this(lastIndication.getId(), lastIndication.getUserIndication().getId(), indicatedResponseDto);
    }
    public IndicationFormResponseDto(List<IndicationForm> indications) {
        this(
                indications.isEmpty() ? null : indications.get(0).getId(),  // Assuming all forms have the same id
                indications.isEmpty() ? null : indications.get(0).getUserIndication().getId(),
                indications.isEmpty() ? new ArrayList<>() : convertToIndicatedResponseDto(indications)
        );
    }

    private static List<IndicatedResponseDto> convertToIndicatedResponseDto(List<IndicationForm> indications) {
        List<IndicatedResponseDto> indicatedResponseDtos = new ArrayList<>();
        for (IndicationForm indicationForm : indications) {
            Indicated indicated = indicationForm.getIndicated();
            if (indicated != null) {
                indicatedResponseDtos.add(new IndicatedResponseDto(indicated.getId(), indicated.getUserIndicated().getName()));
            }
        }
        return indicatedResponseDtos;
    }

}
