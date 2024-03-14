package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.forms.FormIndicationDto;
import com.reviewer.reviewer.dto.forms.IndicadosDto;
import com.reviewer.reviewer.dto.forms.IndicandoDto;
import com.reviewer.reviewer.dto.forms.IndicatedUserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity(name = "Indicando")
@Table(name = "indicando")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Indicando {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "indicado_id")
    private Indicados indicados;


    public Indicando(User user, Indicados indicados) {
        this.usuario = user;
        this.indicados = indicados;
    }
}
