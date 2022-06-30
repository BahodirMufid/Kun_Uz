package com.company.dto;
// PROJECT NAME Kun_Uz
// TIME 11:08
// MONTH 06
// DAY 22

import com.company.dto.article.ArticleDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SavedArticleDTO {

    private Integer id;
    private Integer profileId;
    private String articleId;
    private LocalDateTime createdDate;
}
