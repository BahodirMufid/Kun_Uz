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
@Table(name = "email_history")
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String email; // to_email
    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();






}
