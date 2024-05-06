package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.dto.forms.*;

import com.reviewer.reviewer.models.IndicationForm;

import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IndicationFormService {

    @Autowired
    private IndicationFormRepository indicationFormRepository;

    @Autowired
    private IndicatedRepository indicatedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public EmailService emailService;

    public IndicationFormResponseDto create(@Valid IndicationFormDto form){

        var user = userRepository.findById(form.userIndication());
        List<Indicated> indicatedList = new ArrayList<>();
        List<IndicationForm> indicationUser = new ArrayList<>();

        for (int i = 0; i < form.indicateds().size(); i++) {
            
            var userIndicated = userRepository.findById(form.indicateds().get(i).userIndicated());
            var indicated = new Indicated(userIndicated.get());
            indicatedList.add(indicated);
            var indication = new IndicationForm(user.get(), indicated);



            if(user.get().getId().equals(indicatedList.get(i).getUserIndicated().getId()) && indicatedList.get(i).getUserIndicated().getEmail().equals(userIndicated.get().getEmail())){
                throw new NoSuchElementException("You repeated a user id in your indications! ");
            }
            else {
                indicatedRepository.save(indicated);
                indicationFormRepository.save(indication);
                indicationUser.add(indication);
            } 
          
        }

        List<IndicatedResponseDto> indicatedResponseDtos = IndicatedResponseDto.fromIndicatedList(indicatedList);
        int index = 0;
        String[] indicateds = new String[form.indicateds().size()];
        for (Indicated indicated : indicatedList) {
            System.out.println(form.indicateds());
            indicateds[index] = indicated.getUserIndicated().getEmail();
            index+=1;
        };
    
        var email = new EmailRecordDto(null, "Feedback.BDXD-BR@br.bosch.com",indicateds , null, "Disparo de indicações Email!", "Clique no link abaixo para responder o formulário!\nhttp://10.234.84.188/form");
        emailService.sendMail(email);

        IndicationForm lastIndication = indicationUser.get(indicationUser.size() - 1);
        IndicationFormResponseDto responseDto = new IndicationFormResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        return responseDto;
    }


}
