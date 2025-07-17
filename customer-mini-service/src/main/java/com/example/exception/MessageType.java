package com.example.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    KULLANILMIS_TCKN("999", "Kullanılmış TCKN. Lütfen başka bir TCKN deneyiniz!"),
    KULLANILMIS_EMAIL("555","Kullanılmış e-mail. Lütfen başka bir TCKN deneyiniz!"),
    MUSTERİ_BULUNAMADI("111","Müşteri bulunamadi!"),
    HATALİ_KARAKTER_SAYİSİ("222" , "11 Karakter olması gerekir"),
    İLK_BASAMAK_HATASI("333" , "İlk basamak 0 olamaz."),
    TCKN_BASARISIZ("1000" , "Matematiksel işlemler doğru sonucu vermiyor.");


    private String mesaj;
    private String code;

    MessageType(String code , String mesaj) {
        this.mesaj = mesaj;
        this.code = code;
    }

}
