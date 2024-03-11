package com.reviewer.reviewer.services;

import com.reviewer.reviewer.dto.forms.FormIndicationDto;
import com.reviewer.reviewer.dto.forms.FormIndicationResponseDto;
import com.reviewer.reviewer.dto.forms.IndicatedUserDto;
import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.repositories.FormIndicationRepository;
import com.reviewer.reviewer.repositories.IndicatedUserRepository;
import com.reviewer.reviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormIndicationService {

    @Autowired
    private FormIndicationRepository repository;

    @Autowired
    private IndicatedUserRepository indicatedUserRepository;

    @Autowired
    private UserRepository userRepository;

    public FormIndicationResponseDto create(FormIndicationDto form){

        var user = userRepository.findById(form.indicatingUser());

        var indication = new IndicatedUsers(form.indicatingUser(), user.get());
        var listIndicated = form.indicatedUsers();

        // Primeiro, salve a entidade de indicação principal
        var savedIndication = indicatedUserRepository.save(indication);

        // Em seguida, salve os usuários indicados e associe-os à indicação principal
        for(IndicatedUserDto indicated : listIndicated ) {
            var findUser = userRepository.findById(indicated.user_id());
            var indicatedUser = new IndicatedUsers(savedIndication.getId(), findUser.get());
            indicatedUserRepository.save(indicatedUser);
        }

        // Finalmente, crie a entidade de FormIndication com a indicação principal
        var formIndicatedComplet = new FormIndication(savedIndication, user.get());
        repository.save(formIndicatedComplet);

        return new FormIndicationResponseDto(form);
    }
}
