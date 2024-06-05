package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.IndicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndicationFormRepository extends JpaRepository<IndicationForm, Long> {
    List<IndicationForm> findByUserIndicationId(String indication);
}
