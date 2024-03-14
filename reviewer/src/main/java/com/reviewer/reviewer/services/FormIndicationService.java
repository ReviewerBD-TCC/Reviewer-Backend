package com.reviewer.reviewer.services;

import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.Indicados;
import com.reviewer.reviewer.models.Indicando;
import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormIndicationService {

    @Autowired
    private IndicandoRepository indicandoRepository;

    @Autowired
    private IndicadosRepository indicadosRepository;

    @Autowired
    private UserRepository userRepository;

    public IndicandoResponseDto create(IndicandoDto form){

        var usuario = userRepository.findById(form.indicando());
        List<Indicando> indicandos = new ArrayList<>();

        for (int i = 0; i < form.indicados().size(); i++) {
            var indicado = new Indicados(usuario.get());
            indicadosRepository.save(indicado);

            var indicando = new Indicando(usuario.get(), indicado);
            indicandoRepository.save(indicando);

            indicandos.add(indicando);
        }

        // Use o último Indicando da lista para criar o IndicandoResponseDto
        Indicando ultimoIndicando = indicandos.get(indicandos.size() - 1);
        IndicandoResponseDto responseDto = new IndicandoResponseDto(ultimoIndicando);

        return responseDto;
    }


}
