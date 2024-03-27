package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormIndicationService {

    @Autowired
    private FormIndicationRepository formIndicationRepository;

    @Autowired
    private IndicatedRepository indicatedRepository;

    @Autowired
    private UserRepository userRepository;

    public FormIndicationResponseDto create(IndicationFormDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> userIndicationList = new ArrayList<>();

        for (int i = 0; i < form.indicateds().size(); i++) {
            var userIndicated = userRepository.findById(form.indicateds().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedRepository.save(indicated);

            var indication = new IndicationForm(user.get(), indicated);
            formIndicationRepository.save(indication);

            userIndicationList.add(indication);

            indicatedList.add(indicated);
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicatedList(indicatedList);


        IndicationForm lastIndication = userIndicationList.get(userIndicationList.size() - 1);
        FormIndicationResponseDto responseDto = new FormIndicationResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        return responseDto;
    }


}
