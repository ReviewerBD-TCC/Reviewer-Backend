package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "indication_form")
@Table(name = "indication_forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userIndication")
    private User userIndication;

    @ManyToOne
    @JoinColumn(name = "indicated_id")
    private Indicated indicated;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;



    public IndicationForm(User user, Indicated indicateds, Form form) {

        this.userIndication = user;
        this.indicated = indicateds;
        this.form = form;
    }

    public IndicationForm(User user) {
        this.userIndication = user;

    }
}
