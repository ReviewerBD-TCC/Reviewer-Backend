package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatedRepository extends JpaRepository<Indicated, Long> {
    Indicated findByUserIndicated(User userIndicated);
}
