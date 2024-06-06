package com.reviewer.reviewer.repositories;

import com.reviewer.reviewer.models.Indicated;
import com.reviewer.reviewer.models.IndicationForm;
import com.reviewer.reviewer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndicationFormRepository extends JpaRepository<IndicationForm, Long> {
    @Query(value = "SELECT * FROM indication_forms i WHERE i.indicated_id = :userIndicatedId AND i.user_indication = :userIndication", nativeQuery = true)
    IndicationForm findByUserIndicationAndIndicatedId(@Param("userIndicatedId") Long indicatedId, @Param("userIndication") String userIndication);

    @Query(value = """
            SELECT *
            FROM indication_forms i 
            JOIN indicateds ind ON i.indicated_id = ind.id 
            WHERE i.form_id = :formId 
            AND i.user_indication = :userId 
            AND ind.user_indicated IN (:userIndicatedIds)
            """, nativeQuery = true)
    List<Object> findByFormAndUserAndIndicatedUsers(
            @Param("formId") Long formId,
            @Param("userId") String userId,
            @Param("userIndicatedIds") List<String> userIndicatedIds
    );

    IndicationForm findByIndicatedId(User indicatedId);
}
