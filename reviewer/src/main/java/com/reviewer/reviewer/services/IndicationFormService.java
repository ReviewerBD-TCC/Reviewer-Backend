package com.reviewer.reviewer.services;
import com.reviewer.reviewer.dto.email.EmailRecordDto;
import com.reviewer.reviewer.dto.forms.*;

import com.reviewer.reviewer.models.IndicationForm;

import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    public IndicationFormResponseDto create(@Valid IndicationFormDto form) throws UnknownHostException {

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
    
        InetAddress end = InetAddress.getLocalHost();
        var ip = end.getHostAddress().toString();
        String body = """
                <p>Disparo de indicação referente ao <strong>%s</strong></p>
                <h3>Clique no link abaixo para responder o formulário!!</h3>
                <p>http://%s:5173/form</p>
                <p>Saudações / Best regards,
                   <br>
                   <br>
                   <strong>Bot Feedback</strong>
                   <br>
                   <br>
                   BD/XD-BR Feedback (BD/SWD-FSA1)
                   <br>
                   Robert Bosch Limitada | Caixa Postal 954 | CEP 13012-970 | BRASIL | www.bosch.com.br
                   <br>
                   Feedback.BDXD-BR@br.bosch.com
                   </p>
                
                """.formatted(user.get().getName(), ip);

        var email = new EmailRecordDto( "Feedback.BDXD-BR@br.bosch.com",indicateds ,  "Disparo de indicações Email!", body);
        emailService.sendMail(email);

        IndicationForm lastIndication = indicationUser.get(indicationUser.size() - 1);
        IndicationFormResponseDto responseDto = new IndicationFormResponseDto(lastIndication, user.get(), indicatedResponseDtos);

        return responseDto;
    }


}
