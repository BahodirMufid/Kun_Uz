package com.company.service;

import com.company.entity.ArticleEntity;
import com.company.entity.ProfileEntity;
import com.company.entity.SavedArticleEntity;
import com.company.enums.SaveArticleStatus;
import com.company.exps.ItemNotFoundException;
import com.company.repository.ArticleRepository;
import com.company.repository.SavedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

// PROJECT NAME Kun_Uz
// TIME 11:13
// MONTH 06
// DAY 22

@Service
public class SavedArticleService {

    @Autowired
    private SavedArticleRepository savedArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public void articleSave(String articleId, Integer pId) {
        likeDislike(articleId, pId, SaveArticleStatus.SAVED);
    }

//    public void articleNotSave(String articleId, Integer pId) {
//        likeDislike(articleId, pId, SaveArticleStatus.NOT_SAVED);
//    }


    private void likeDislike(String articleId, Integer pId, SaveArticleStatus status) {
        Optional<SavedArticleEntity> optional = savedArticleRepository.findExists(articleId, pId);
        if (optional.isPresent()) {
            SavedArticleEntity like = optional.get();
            like.setStatus(status);
            savedArticleRepository.save(like);
            return;
        }
        boolean articleExists = articleRepository.existsById(articleId);
        if (!articleExists) {
            throw new ItemNotFoundException("Article NotFound");
        }

        SavedArticleEntity like = new SavedArticleEntity();
        like.setArticle(new ArticleEntity(articleId));
        like.setProfile(new ProfileEntity(pId));
        like.setStatus(status);
        savedArticleRepository.save(like);
    }

    public void removeLike(String articleId, Integer pId) {
       /* Optional<ArticleLikeEntity> optional = articleLikeRepository.findExists(articleId, pId);
        optional.ifPresent(articleLikeEntity -> {
            articleLikeRepository.delete(articleLikeEntity);
        });*/
        savedArticleRepository.delete(articleId, pId);
    }


}
