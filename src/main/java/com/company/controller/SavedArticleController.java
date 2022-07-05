package com.company.controller;


import com.company.dto.SavedArticleDTO;
import com.company.service.SavedArticleService;
import com.company.util.HttpHeaderUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/article_save")
public class SavedArticleController {
    @Autowired
    SavedArticleService savedArticleService;

    @ApiOperation(value = " Save Article ", notes = "Method for Save Article")
    @PostMapping("/save")
    public ResponseEntity<Void> articleSave(@RequestBody SavedArticleDTO dto
                                             ) {
        savedArticleService.articleSave(dto.getArticleId());
        return ResponseEntity.ok().build();
    }


//    @PostMapping("/notSave")
//    public ResponseEntity<Void> ArticleNotSave(@RequestBody SavedArticleDTO dto
//                                                ) {
//        savedArticleService.articleNotSave(dto.getArticleId());
//        return ResponseEntity.ok().build();
//    }


    @ApiOperation(value = "Remove Save Article ", notes = "Method for Remove Save Article")
    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody SavedArticleDTO dto
                                        ) {
        savedArticleService.removeLike(dto.getArticleId());
        return ResponseEntity.ok().build();
    }
}
