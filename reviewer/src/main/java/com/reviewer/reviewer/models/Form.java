package com.reviewer.reviewer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "form")
@Entity(name = "forms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate year;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate validation;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormQuestion> formQuestions;
}