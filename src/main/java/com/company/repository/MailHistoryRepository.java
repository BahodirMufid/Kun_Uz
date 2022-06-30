package com.company.repository;
// PROJECT NAME Kun_Uz
// TIME 17:45
// MONTH 06
// DAY 22


import com.company.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
    @Query(value = "select count(*) " +
            "from email_history " +
            "where email = :email " +
            "and created_date + INTERVAL '1 MINUTE' > now() ", nativeQuery = true)
    Long countResend(@Param("email") String toAccount);
}
