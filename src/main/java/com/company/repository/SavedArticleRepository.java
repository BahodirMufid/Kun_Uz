package com.company.repository;


import com.company.entity.SavedArticleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface SavedArticleRepository extends CrudRepository<SavedArticleEntity, Integer> {
    @Query("FROM SavedArticleEntity a where  a.article.id=:articleId and a.profile.id =:profileId")
    Optional<SavedArticleEntity> findExists(@Param("articleId") String articleId, @Param("profileId") Integer pId);


//    Optional<ArticleLikeEntity> findByArticleAndProfile(ArticleEntity article, ProfileEntity profile);
//
//    @Query("FROM ArticleLikeEntity a where  a.article.id=:articleId and a.profile.id =:profileId")
//    Optional<ArticleLikeEntity> findExists(String articleId, Integer profileId);


    @Transactional
    @Modifying
    @Query("DELETE FROM SavedArticleEntity a where  a.article.id=:articleId and a.profile.id =:profileId")
    void delete(@Param("articleId") String articleId, @Param("profileId") Integer pId);


//    @Query(value = "SELECT " +
//            "    cast(SUM (CASE  WHEN  status='LIKE' THEN 1 ELSE 0 END) as int) AS like_count, " +
//            "    cast(SUM (CASE  WHEN  status='DISLIKE' THEN 1 ELSE 0 END) as int) AS dislike_count " +
//            "FROM " +
//            "    article_like where article_id = :articleId;", nativeQuery = true)
//    Integer countByArticle(@Param("articleId") String articleId);

}
