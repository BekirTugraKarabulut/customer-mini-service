package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMusteriUI {

    private int musteriId;

    @Max(value = 11 , message = "Lütfen 11 rakamlı giriniz!")
    @Min(value = 11 , message = "Lütfen 11 rakamlı giriniz!")
    private String tckn;

    private String musteriAd;

    private String musteriSoyad;

    private String babaAdi;

    private String anneAdi;

    private String email;

    private String parola;

    private String adres;

}
