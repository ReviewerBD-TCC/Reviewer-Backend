package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    Dashboard findByFormId(Long formId);
}
