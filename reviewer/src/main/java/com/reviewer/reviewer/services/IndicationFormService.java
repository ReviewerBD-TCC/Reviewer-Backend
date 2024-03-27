package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.forms.*;

import com.reviewer.reviewer.models.IndicationForm;

import com.reviewer.reviewer.models.Indicated;
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


    public FormIndicationResponseDto create(IndicationFormDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> userIndicationList = new ArrayList<>();
    }
    public IndicationFormResponseDto create(@Valid IndicationFormDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> indicationUser = new ArrayList<>();

        for (int i = 0; i < form.indicateds().size(); i++) {
            var userIndicated = userRepository.findById(form.indicateds().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedRepository.save(indicated);

            var indication = new IndicationForm(user.get(), indicated);

            formIndicationRepository.save(indication);

            indicationFormRepository.save(indication);


            userIndicationList.add(indication);

            indicatedList.add(indicated);
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicatedList(indicatedList);


        IndicationForm lastIndication = userIndicationList.get(userIndicationList.size() - 1);
        FormIndicationResponseDto responseDto = new FormIndicationResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        IndicationForm lastIndication = indicationUser.get(indicationUser.size() - 1);
        IndicationFormResponseDto responseDto = new IndicationFormResponseDto(lastIndication, user.get(), indicatedResponseDtos);


        return responseDto;
    }


}
