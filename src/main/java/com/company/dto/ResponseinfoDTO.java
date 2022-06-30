package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseinfoDTO {
    private int status;
    private String messaga;

    public ResponseinfoDTO(int status, String messaga) {
        this.status = status;
        this.messaga = messaga;
    }

    public ResponseinfoDTO(int status) {
        this.status = status;
    }

    public ResponseinfoDTO() {
    }
}