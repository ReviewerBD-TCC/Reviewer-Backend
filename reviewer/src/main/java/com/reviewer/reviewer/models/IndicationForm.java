package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "indication", cascade = CascadeType.ALL)
    private List<QuestionForm> questionForm;




    public IndicationForm(User user, Indicated indicateds) {

        this.userIndication = user;
        this.indicated = indicateds;
    }

    public IndicationForm(User user) {
        this.userIndication = user;

    }
}
