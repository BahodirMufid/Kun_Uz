package com.company.entity;
// PROJECT NAME Kun_Uz
// TIME 11:00
// MONTH 06
// DAY 22

import com.company.enums.LikeStatus;
import com.company.enums.SaveArticleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "saved_article")
public class SavedArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private SaveArticleStatus status = SaveArticleStatus.NOT_SAVED;


    @JoinColumn(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ArticleEntity article;

    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

}
