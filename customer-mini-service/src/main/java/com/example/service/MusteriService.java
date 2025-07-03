package com.example.service;

import com.example.dto.DtoMusteri;
import com.example.dto.DtoMusteriUI;

import java.util.List;

public interface MusteriService {

    public List<DtoMusteri> getAllMusteri();

    public DtoMusteri getMusteriByTckn(String tckn);

    public DtoMusteri saveMusteri(DtoMusteriUI dtoMusteriUI);

    public DtoMusteri updateMusteri(String tckn ,DtoMusteriUI dtoMusteriUI);

    public void deleteMusteri(String tckn);

}
