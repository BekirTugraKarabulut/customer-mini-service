package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musteri")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musteri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "musteri_id")
    private int musteriId;

    @Column(name = "tckn" , unique = true)
    private String tckn;

    @Column(name = "musteri_ad")
    private String musteriAd;

    @Column(name = "musteri_soyad")
    private String musteriSoyad;

    @Column(name = "baba_adi")
    private String babaAdi;

    @Column(name = "anne_adi")
    private String anneAdi;

    @Column(name = "e-mail" , unique = true)
    private String email;

    @Column(name = "parola")
    private String parola;

    @Column(name = "adres")
    private String adres;

}
