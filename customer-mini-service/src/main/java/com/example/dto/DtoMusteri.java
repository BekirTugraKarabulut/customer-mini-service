package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMusteri {

    private int musteriId;

    private String tckn;

    private String musteriAd;

    private String musteriSoyad;

    private String babaAdi;

    private String anneAdi;

    private String email;

    private String parola;

    private String adres;

}
