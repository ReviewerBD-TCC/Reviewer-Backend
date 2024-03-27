package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndicationFormService {

    @Autowired
    private IndicationFormRepository indicationFormRepository;

    @Autowired
    private IndicatedRepository indicatedRepository;

    @Autowired
    private UserRepository userRepository;

    public IndicationFormResponseDto create(@Valid IndicationFormDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> indicationUser = new ArrayList<>();

        for (int i = 0; i < form.indicados().size(); i++) {
            var userIndicated = userRepository.findById(form.indicados().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedRepository.save(indicated);

            var indication = new IndicationForm(user.get(), indicated);
            indicationFormRepository.save(indication);

            indicationUser.add(indication);

            indicatedList.add(indicated);
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicadosList(indicatedList);


        IndicationForm lastIndication = indicationUser.get(indicationUser.size() - 1);
        IndicationFormResponseDto responseDto = new IndicationFormResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        return responseDto;
    }


}
