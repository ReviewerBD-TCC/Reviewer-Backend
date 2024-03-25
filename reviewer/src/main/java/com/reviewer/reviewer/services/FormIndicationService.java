package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.FormIndication;
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

    public FormIndicationResponseDto create(FormIndicationDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<FormIndication> indicationUser = new ArrayList<>();

        for (int i = 0; i < form.indicados().size(); i++) {
            var userIndicated = userRepository.findById(form.indicados().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedRepository.save(indicated);

            var indication = new FormIndication(user.get(), indicated);
            formIndicationRepository.save(indication);

            indicationUser.add(indication);

            indicatedList.add(indicated);
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicadosList(indicatedList);


        FormIndication lastIndication = indicationUser.get(indicationUser.size() - 1);
        FormIndicationResponseDto responseDto = new FormIndicationResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        return responseDto;
    }


}
