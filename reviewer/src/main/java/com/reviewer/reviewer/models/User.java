package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.users.UserResponseDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "users")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<QuestionAnswer> questionAnswers;

    @OneToMany(mappedBy = "userIndicated", cascade = CascadeType.ALL)
    private List<Indicated> userIndicated;

    @OneToMany(mappedBy = "userIndication", cascade = CascadeType.ALL)
    private List<IndicationForm> userIndication;


    public User(UserResponseDto data) {
        this.id = data.id();
        this.name = data.name();
        this.email = data.mail();

    }

}