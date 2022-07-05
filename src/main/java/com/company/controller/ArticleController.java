package com.company.controller;


import com.company.dto.article.ArticleCreateDTO;
import com.company.dto.article.ArticleDTO;
import com.company.dto.article.ArticleFilterDTO;
import com.company.dto.article.ArticleRequestDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.ArticleService;
import com.company.util.HttpHeaderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "Article TODOS")
@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    //1
    @ApiOperation(value = "Create", notes = "Method for Article Create for Moderator")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody @Valid ArticleCreateDTO articleDTO,
                                    HttpServletRequest request) {

//        Integer profileId = HttpHeaderUtil.getId(request, ProfileRole.MODERATOR);

        ArticleCreateDTO dto = articleService.create(articleDTO);
        return ResponseEntity.ok().body(dto);
    }

    //2
    @ApiOperation(value = "Update", notes = "Method for Article Update by description ")
    @PostMapping("/adm/update/{description}")
    public ResponseEntity<?> update(@PathVariable("description") String description,
                                    @RequestBody ArticleCreateDTO articleDTO) {
        articleService.update(description, articleDTO);
        return ResponseEntity.ok().body("Successfully updated");
    }

    //3
    @ApiOperation(value = "Delete", notes = "Method for Article delete by description ")
    @DeleteMapping("/delete/{description}")
    public ResponseEntity<?> delete(@PathVariable("description") String description) {

        articleService.delete(description);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully deleted");
        return build;
    }

    //4
    @ApiOperation(value = " Publish Article", notes = "Method for Article update by status for Publisher")
    @PutMapping("/adm/status/{id}")
    public ResponseEntity<Void> updateByStatus(@PathVariable("id") String id) {

        articleService.updateByStatus(id);
        return ResponseEntity.ok().build();
    }


    //5
    @ApiOperation(value = " get Top 5 Article List By Type Key ", notes = "Method for Article get Top 5 Article List By Type Key")
    @GetMapping("/getListByTypeKey/{key}")
    public ResponseEntity<?> getListByTypeKey(@PathVariable("key") String key) {

        List<ArticleDTO> list = articleService.findTop5ArticlesByTypeKey(key);
        return ResponseEntity.ok().body(list);
    }

    //6
    @ApiOperation(value = " get Top 3 Article List By Type Key ", notes = "Method for Article get Top 3 Article List By Type Key")
    @GetMapping("/getListByTKey/{key}")
    public ResponseEntity<?> getListByTKey(@PathVariable("key") String key) {

        List<ArticleDTO> list = articleService.findTop5ArticlesByTyKey(key);
        return ResponseEntity.ok().body(list);
    }


    //7
    @ApiOperation(value = " get Last 8 Article List Not in ", notes = "Method for Article get Last 8 Article List Not in")
    @PostMapping("/last8")
    public ResponseEntity<List<ArticleDTO>> getLast8NotIn(@RequestBody ArticleRequestDTO dto) {
        List<ArticleDTO> response = articleService.getLat8ArticleNotIn(dto.getIdList());
        return ResponseEntity.ok().body(response);
    }

    //8
    @ApiOperation(value = " get Article Last by Lang", notes = "Method for Article  get Article Last by Lang")
    @GetMapping("/getArticle/{id}")
    public ResponseEntity<?> getArticleList(@PathVariable("id") String id,
                                            @RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                            Language lang) {
        ArticleDTO list = articleService.getArticleById(id, lang);
        return ResponseEntity.ok().body(list);
    }

    //9
    @ApiOperation(value = " get Article by type key ", notes = "Method for Article get Article by type key")
    @GetMapping("/type/{key}")
    public ResponseEntity<?> getListByType(@PathVariable("key") String key, @PathVariable("id") String id) {

        List<ArticleDTO> list = articleService.getLast5ArticleByType(key, id);
        return ResponseEntity.ok().body(list);
    }


    //10
    @ApiOperation(value = " get Most Read Article ", notes = "Method for Article get Most Read Article")
    @GetMapping("/mostRead")
    public ResponseEntity<?> getMostReadArticleByViewCount() {

        List<ArticleDTO> list = articleService.getMost4ReadArticleByViewCount();
        return ResponseEntity.ok().body(list);
    }

    //11
    @ApiOperation(value = " get Article by tag name ", notes = "Method for Article get Article by Tag name")
    @GetMapping("/byTagName/{tagName}")
    public ResponseEntity<?> get5ArticlesByTag(@PathVariable("tagName") String tagName) {

        List<ArticleDTO> list = articleService.get5ArticlesByTagName(tagName);
        return ResponseEntity.ok().body(list);
    }

    //12
    @ApiOperation(value = " get Article by region and type key ", notes = "Method for Article get Article by region and type key")
    @GetMapping("/regionAndType/{typeKey}/{regionKey}")
    public ResponseEntity<?> get5ArticlesByRegionAndType(@PathVariable("typeKey") String typeKey,
                                                         @PathVariable("regionKey") String regionKey) {

        List<ArticleDTO> list = articleService.getMost4ReadArticleByTypesAndRegion(typeKey, regionKey);
        return ResponseEntity.ok().body(list);
    }

    //13
    @ApiOperation(value = " get Article by region key ", notes = "Method for Article get Article by region key")
    @GetMapping("/byRegionKey/{key}")
    public ResponseEntity<?> getLastArticleByRegionKey(@PathVariable("key") String key,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "5") int size) {

        PageImpl<ArticleDTO> list = articleService.getLast5ArticleByRegionKey(key, page, size);
        return ResponseEntity.ok().body(list);
    }

    //14
    @ApiOperation(value = " get Article by category key ", notes = "Method for Article get Article by category key")
    @GetMapping("/getListByCategoryKey/{key}")
    public ResponseEntity<?> getListByCategoryKey(@PathVariable("key") String key) {

        List<ArticleDTO> list = articleService.findTop5ByArticleByCategory(key);
        return ResponseEntity.ok().body(list);
    }


    //15
    @ApiOperation(value = " get Article by category key ", notes = "Method for Article get Article by category key")
    @GetMapping("/getCategoryKey/{key}")
    public ResponseEntity<?> getListByCategory(@PathVariable("key") String key) {

        List<ArticleDTO> list = articleService.getCategoryByPage(key);
        return ResponseEntity.ok().body(list);
    }


    //16 ,17 , 18 in ArticleLikeController

    //19
    @ApiOperation(value = " Increase View Count ", notes = "Method for Article increase view count")
    @PostMapping("/increaseViewCount/{id}")
    public ResponseEntity<?> increaseViewCountByArticleId(@PathVariable("id") String id) {
        articleService.increaseViewCountById(id);

        return ResponseEntity.ok().build();
    }


    //20

    @ApiOperation(value = " Get Article List ", notes = "Method for Article List")
    @GetMapping("/getList")
    public ResponseEntity<?> getArticleList() {
        List<ArticleDTO> list = articleService.getArticles();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = " Get Article filter ", notes = "Method for Article Filter")
    @PostMapping("/adm/filter")
    public ResponseEntity<List<ArticleDTO>> getFilter(@RequestBody ArticleFilterDTO dto) {
        List<ArticleDTO> filter = articleService.filter(dto);
        return ResponseEntity.ok().body(filter);
    }


}
