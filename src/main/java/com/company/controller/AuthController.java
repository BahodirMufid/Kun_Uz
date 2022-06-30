package com.company.controller;

import com.company.dto.*;
import com.company.service.AuthService;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(tags = "Authorization and Registration")
@RestController
@RequestMapping("/auth")
public class AuthController {

//    Logger log= LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Registration", notes = "Method for Registration")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDTO dto) {
        String response = authService.registration(dto);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Login", notes = "Method for Authorization")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO dto) {
        log.info("Request for login {}",dto);
        ProfileDTO profileDto = authService.login(dto);
        return ResponseEntity.ok(profileDto);
    }


    @ApiOperation(value = "Sms Verification", notes = "Method for sms Verification")
    @PostMapping("/verification")
    public ResponseEntity<String> login(@RequestBody VerificationDTO dto) {
        String response = authService.verification(dto);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Resend Sms", notes = "Method for Resend Sms")
    @GetMapping("/resend/{phone}")
    public ResponseEntity<ResponseinfoDTO> loginPhone(@ApiParam(value = "phone", required = true, example = "998935997864")
                                                      @PathVariable("phone") String phone) {
        ResponseinfoDTO responseinfoDTO = authService.resendSms(phone);
        return ResponseEntity.ok(responseinfoDTO);
    }

    @GetMapping("/resend/{jwt}")
    public ResponseEntity<?> loginJwt(@PathVariable("jwt") String jwt) {
        Integer id = JwtUtil.decode(jwt);

        EmailRequestDTO dto = authService.resend(id);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Email Verification", notes = "Method for Email Verification")
    @GetMapping("/email/verification/{id}")
    public ResponseEntity<String> login(@PathVariable("id") Integer id) {
        String response = authService.emailVerification(id);
        return ResponseEntity.ok(response);
    }

}
