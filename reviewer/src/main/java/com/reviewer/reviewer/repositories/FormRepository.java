package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
