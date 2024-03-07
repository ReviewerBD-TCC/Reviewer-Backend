package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.forms.FormIndicationDto;
import com.reviewer.reviewer.dto.forms.IndicatedUserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "form_indication")
@Entity(name = "forms_indications")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FormIndication {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "indicating_user_id")
    private User indicatingUser;

    @ManyToOne
    @JoinColumn(name = "indicatedUsers_id")
    private IndicatedUsers indicatedUsers;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<IndicatedUsers> fk_formIndication;


    public FormIndication(Long id, User user, IndicatedUsers indicatedUsers) {
        this.id = id;
        this.indicatingUser = user;
        this.indicatedUsers = indicatedUsers;
    }
}
