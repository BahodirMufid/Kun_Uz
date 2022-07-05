package com.company.controller;




import com.company.dto.article.ArticleLikeDTO;
import com.company.service.ArticleLikeService;
import com.company.util.HttpHeaderUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@RequestMapping("/article_like")
@RestController
public class ArticleLikeController {


    @Autowired
    private ArticleLikeService articleLikeService;
    @ApiOperation(value = " Like ", notes = "Method for Article Like")
    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody ArticleLikeDTO dto) {
        articleLikeService.articleLike(dto.getArticleId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = " Dislike ", notes = "Method for Article Dislike")
    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody ArticleLikeDTO dto) {

        articleLikeService.articleDisLike(dto.getArticleId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = " Remove ", notes = "Method for Article Remove")
    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody ArticleLikeDTO dto
                                     ) {
        articleLikeService.removeLike(dto.getArticleId());
        return ResponseEntity.ok().build();
    }

}
