package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.enums.Language;
import com.company.enums.ProfileRole;
import com.company.service.RegionService;
import com.company.util.HttpHeaderUtil;
import com.company.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/region")
@RestController
public class RegionController {
    @Autowired
    private RegionService regionService;

    // PUBLIC
    @GetMapping("/public")
    public ResponseEntity<List<RegionDTO>> getRegionListByLanguage(@RequestHeader(value = "Accept-Language" , defaultValue = "uz")
                                                                   Language language){
        List<RegionDTO> list = regionService.getList(language);
        return ResponseEntity.ok().body(list);
    }


    // SECURE
    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDto) {

        regionService.create(regionDto);
        return ResponseEntity.ok().body("Successfully created");
    }

    @GetMapping("/adm/list")
    public ResponseEntity<List<RegionDTO>> getList() {

        List<RegionDTO> list = regionService.getListOnlyForAdmin();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/adm/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Integer id,
                                     @RequestBody RegionDTO dto
                                      ) {
        regionService.update(id, dto);
        return ResponseEntity.ok().body("Succsessfully updated");
    }

    @DeleteMapping("/adm/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Integer id
                                      ) {
        regionService.delete(id);
        return ResponseEntity.ok().body("Successfully deleted");
    }



}
