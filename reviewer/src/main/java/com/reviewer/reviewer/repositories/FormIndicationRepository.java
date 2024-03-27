package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.IndicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormIndicationRepository extends JpaRepository<IndicationForm, Long> {
}
