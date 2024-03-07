package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.forms.IndicatedUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Table(name = "indicated_user")
@Entity(name = "indicated_users")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class IndicatedUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<FormIndication> formIndication;

    @ManyToOne
    @JoinColumn(name = "userIndicated")
    private User userIndicated;

    @ManyToOne
    @JoinColumn(name = "id_formIndicated")
    private FormIndication id_formIdicated;

    public IndicatedUsers(Long id, User user) {
        this.id = id;
        this.userIndicated = user;
    }
}
