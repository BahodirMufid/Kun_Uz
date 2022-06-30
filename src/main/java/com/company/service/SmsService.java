package com.company.service;

import com.company.dto.integration.SmsRequestDTO;
import com.company.dto.integration.SmsResponseDTO;
import com.company.entity.SmsEntity;
import com.company.repository.SmsRepository;
import com.company.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


// PROJECT NAME Kun_Uz
// TIME 17:45
// MONTH 06
// DAY 22
@Service
public class SmsService {
    @Value("${sms.url}")
    private String smsUrl;
    @Value("${sms.key}")
    private String key;

    @Autowired
    private SmsRepository smsRepository;


    public void sendRegistrationSms(String phone) {
        String code = RandomUtil.getRandomSmsCode();
        String message = "Kun.uz partali uchun\n registratsiya kodi: " + code;

        SmsResponseDTO responseDTO = send(phone, message);

        SmsEntity entity = new SmsEntity();
        entity.setPhone(phone);
        entity.setCode(code);
        entity.setStatus(responseDTO.getSuccess());

        smsRepository.save(entity);
    }

    private SmsResponseDTO send(String phone, String message) {
        SmsRequestDTO requestDTO = new SmsRequestDTO();
        requestDTO.setKey(key);
        requestDTO.setPhone(phone);
        requestDTO.setMessage(message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SmsRequestDTO> entity = new HttpEntity<SmsRequestDTO>(requestDTO, headers);

        RestTemplate restTemplate = new RestTemplate();
        SmsResponseDTO response = restTemplate.postForObject(smsUrl, entity, SmsResponseDTO.class);
        System.out.println("SMS response  => "+response);
        return response;
    }


    public Long getSmsCount (String phone){
        return smsRepository.getSmsCount(phone);
    }
}
