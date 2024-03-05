package com.reviewer.reviewer.models;

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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private User users;

}
