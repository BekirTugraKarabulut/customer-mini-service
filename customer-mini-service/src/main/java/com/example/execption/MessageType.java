package com.example.execption;

import lombok.Getter;

@Getter
public enum MessageType {

    KULLANILMIS_TCKN("999", "Kullanılmış TCKN. Lütfen başka bir TCKN deneyiniz!"),
    KULLANILMIS_EMAIL("555","Kullanılmış e-mail. Lütfen başka bir TCKN deneyiniz!"),
    MUSTERİ_BULUNAMADI("111","Müşteri bulunamadi!");

    private String mesaj;
    private String code;

    MessageType(String code , String mesaj) {
        this.mesaj = mesaj;
        this.code = code;
    }

}
