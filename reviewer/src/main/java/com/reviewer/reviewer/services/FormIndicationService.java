package com.reviewer.reviewer.services;

import com.reviewer.reviewer.dto.forms.*;
import com.reviewer.reviewer.models.FormIndication;
import com.reviewer.reviewer.models.Indicados;
import com.reviewer.reviewer.models.Indicando;
import com.reviewer.reviewer.models.IndicatedUsers;
import com.reviewer.reviewer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Indicados> indicados = new ArrayList<>();
        List<Indicando> indicandoUser = new ArrayList<>();

        for (int i = 0; i < form.indicados().size(); i++) {
            var usuarioIndicado = userRepository.findById(form.indicados().get(i).usuario());
            var indicado = new Indicados(usuarioIndicado.get());
            indicadosRepository.save(indicado);

            var indicando = new Indicando(usuario.get(), indicado);
            indicandoRepository.save(indicando);

            indicandoUser.add(indicando);

            indicados.add(indicado);
        }

        List<IndicadosResponseDto> indicadosResponseDtos = IndicadosResponseDto.fromIndicadosList(indicados);


        Indicando ultimoIndicando = indicandoUser.get(indicandoUser.size() - 1);
        IndicandoResponseDto responseDto = new IndicandoResponseDto(ultimoIndicando, usuario.get(), indicadosResponseDtos);

        return responseDto;
    }


}
