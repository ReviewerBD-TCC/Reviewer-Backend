package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int formQuantitySent;

    public Dashboard(int formQuantitySent, int quantityAnsweredFormSent, Form form) {
        this.formQuantitySent = formQuantitySent;
        this.quantityAnsweredFormSent = quantityAnsweredFormSent;
        this.form = form;
    }

    private int quantityAnsweredFormSent;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;
}
