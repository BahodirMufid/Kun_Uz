package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.ProfileFilterDTO;
import com.company.dto.article.ArticleDTO;
import com.company.dto.article.ArticleFilterDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("adm/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDto) {
        ProfileDTO dto = profileService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("adm/auth")
    public ResponseEntity<List<ProfileDTO>> getProfileList() {


        List<ProfileDTO> list = profileService.getList();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/detail")
    public ResponseEntity<?> update(@RequestBody ProfileDTO dto) {


        profileService.update(dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @PutMapping("/adm/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody ProfileDTO dto) {

        profileService.update(dto);
        return ResponseEntity.ok().body("Successfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        profileService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }

    @GetMapping("/adm/pagination")
        public ResponseEntity<PageImpl> pagination(@RequestParam(value = "page" , defaultValue = "1") int page,
                                                   @RequestParam(value = "size" ,defaultValue = "5" ) int size){

        PageImpl response = profileService.paginationProfile(page , size);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/adm/filter")
    public ResponseEntity<List<ProfileDTO>> getFilter(@RequestBody ProfileFilterDTO dto) {

        List<ProfileDTO> filter = profileService.filter(dto);
        return ResponseEntity.ok().body(filter);
    }



}
