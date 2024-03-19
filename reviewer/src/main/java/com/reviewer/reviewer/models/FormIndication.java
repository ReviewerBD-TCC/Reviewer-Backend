package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Indicando")
@Table(name = "indicando")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormIndication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userIndication")
    private User userIndication;

    @ManyToOne
    @JoinColumn(name = "indicated_id")
    private Indicated indicated;


    public FormIndication(User user, Indicated indicados) {
        this.userIndication = user;
        this.indicated = indicados;
    }

    public FormIndication(Long id, User user) {
        this.id = id;
        this.userIndication = user;

    }
}
