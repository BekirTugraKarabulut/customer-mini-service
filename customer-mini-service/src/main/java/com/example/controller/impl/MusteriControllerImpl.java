package com.example.controller.impl;

import com.example.controller.MusteriController;
import com.example.dto.DtoMusteri;
import com.example.dto.DtoMusteriUI;
import com.example.service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api")
public class MusteriControllerImpl implements MusteriController {

    @Autowired
    private MusteriService musteriService;

    @Override
    @GetMapping(path = "/all-musteri")
    public List<DtoMusteri> getAllMusteri() {
        return musteriService.getAllMusteri();
    }

    @Override
    @GetMapping(path = "/musteri/{tckn}")
    public DtoMusteri getMusteriByTckn(@PathVariable(name = "tckn" ,required = true) String tckn) {
        return musteriService.getMusteriByTckn(tckn);
    }

    @Override
    @PostMapping(path = "/musteri-ekle")
    public DtoMusteri saveMusteri(@RequestBody DtoMusteriUI dtoMusteriUI) {
        return musteriService.saveMusteri(dtoMusteriUI);
    }

    @Override
    @PutMapping(path = "/guncelle/{tckn}")
    public DtoMusteri updateMusteri(@PathVariable(name = "tckn" , required = true) String tckn, @RequestBody DtoMusteriUI dtoMusteriUI) {
        return musteriService.updateMusteri(tckn, dtoMusteriUI);
    }

    @Override
    @DeleteMapping(path = "/musteri-sil/{tckn}")
    public void deleteMusteri(@PathVariable(name = "tckn") String tckn) {
        musteriService.deleteMusteri(tckn);
    }


}
