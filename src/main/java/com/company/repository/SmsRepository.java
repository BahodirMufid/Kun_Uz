package com.company.repository;
// PROJECT NAME Kun_Uz
// TIME 17:45
// MONTH 06
// DAY 22


import com.company.entity.SmsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SmsRepository extends CrudRepository<SmsEntity,Integer> {

    Optional<SmsEntity> findTopByPhoneOrderByCreatedDateDesc(String phone);

    @Query(value = "select * from sms where phone = :phone and created_date > now() - INTERVAL '1 MINUTE' ", nativeQuery = true)
    Long getSmsCount(@Param("phone") String phone);
}
