package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// PROJECT NAME Kun_Uz
// TIME 17:46
// MONTH 06
// DAY 22
@Entity
@Getter
@Setter
@Table(name = "sms")
public class SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private  Boolean status = Boolean.FALSE;




}
