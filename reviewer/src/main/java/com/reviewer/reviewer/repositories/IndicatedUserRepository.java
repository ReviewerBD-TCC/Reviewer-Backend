package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.IndicatedUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatedUserRepository extends JpaRepository<IndicatedUsers, Long> {
}
