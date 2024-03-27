package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "indicateds")
@Entity(name = "indicated")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Indicated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "indicated", cascade = CascadeType.ALL)
    private List<IndicationForm> indicationForms;

    @ManyToOne
    @JoinColumn(name = "userIndicated")
    private User userIndicated;


    public Indicated(User user) {
        this.userIndicated = user;
    }
}
