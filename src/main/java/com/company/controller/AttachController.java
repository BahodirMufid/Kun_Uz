package com.company.controller;

import com.company.dto.AttachDTO;
import com.company.enums.ProfileRole;
import com.company.service.AttachService;
import com.company.util.HttpHeaderUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

// PROJECT NAME Kun_Uz
// TIME 17:06
// MONTH 06
// DAY 20
@Slf4j
@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @ApiOperation(value = " Upload Attach ", notes = "Method for Upload Attach")
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }

    @ApiOperation(value = " Open Attach (Image)", notes = "Method for Open Attach (Image)")
    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }

    @ApiOperation(value = " Open Attach (All)", notes = "Method for Open Attach (All)")
    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }

    @ApiOperation(value = " Download Attach ", notes = "Method for Download Attach ")
    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        return attachService.download(fileName);
    }

    @ApiOperation(value = " Delete Attach ", notes = "Method for Delete Attach (Admin) ")
    @DeleteMapping("/adm/delete/{fileName}")
    public ResponseEntity<?> delete(@PathVariable("fileName") String id) {

        String response = attachService.delete(id);
        return ResponseEntity.ok().body(response);
    }


}
