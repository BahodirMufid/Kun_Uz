package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.dto.article.TypesDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.TypesService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/types")
@RestController
public class ArticleTypeController {
    @Autowired
    private TypesService typesService;

    // PUBLIC
    @ApiOperation(value = " Get Article Type ", notes = "Method for Get Article Type by Language")
    @GetMapping("/public/lang")
    public ResponseEntity<List<TypesDTO>> getTypeListByLanguage(@RequestHeader(value = "Accept-Language", defaultValue = "uz")
                                                                Language language) {
        List<TypesDTO> list = typesService.getList(language);
        return ResponseEntity.ok().body(list);
    }

    // SECURED
    @ApiOperation(value = " Create Type  ", notes = "Method for Create Type by Admin")
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody TypesDTO typesDto) {

        typesService.create(typesDto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @ApiOperation(value = " Get Type List ", notes = "Method for Get Type List by Admin")
    @GetMapping("/adm/list")
    public ResponseEntity<List<TypesDTO>> getList() {
        List<TypesDTO> list = typesService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = " Update Type ", notes = "Method for Update Type by Admin")
    @PutMapping("/adm/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody RegionDTO dto) {
        typesService.update(id, dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @ApiOperation(value = " Delete Type ", notes = "Method for Delete Type by Admin")
    @DeleteMapping("/adm/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        typesService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }

    @ApiOperation(value = " Pagination Type List ", notes = "Method for Pagination Type List")
    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "5") int size) {

//        List<TypesDTO> list = typesService.getPagination(page , size);
        PageImpl response = typesService.paginationTypes(page, size);
        return ResponseEntity.ok().body(response);
    }


}
