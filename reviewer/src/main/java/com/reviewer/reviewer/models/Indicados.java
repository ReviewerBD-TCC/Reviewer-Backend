package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.forms.IndicadosDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "indicados")
@Entity(name = "Indicado")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Indicados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "indicados", cascade = CascadeType.ALL)
    private List<Indicando> indicando;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private User usuario;


    public Indicados(User user) {
        this.usuario = user;
    }
}
