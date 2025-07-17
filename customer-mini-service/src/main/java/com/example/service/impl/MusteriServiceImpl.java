package com.example.service.impl;

import com.example.dto.DtoMusteri;
import com.example.dto.DtoMusteriUI;
import com.example.entity.Musteri;
import com.example.exception.BaseException;
import com.example.exception.ErrorMessage;
import com.example.exception.MessageType;
import com.example.repository.MusteriRepository;
import com.example.service.MusteriService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusteriServiceImpl implements MusteriService {

    @Autowired
    private MusteriRepository musteriRepository;

    @Override
    public List<DtoMusteri> getAllMusteri() {

        List<Musteri> musteriList = musteriRepository.findAll();
        List<DtoMusteri> dtoMusteriList = new ArrayList<>();

        for (Musteri musteri : musteriList) {
            DtoMusteri dtoMusteri = new DtoMusteri();
            BeanUtils.copyProperties(musteri, dtoMusteri);
            dtoMusteriList.add(dtoMusteri);
        }

        return dtoMusteriList;
    }

    @Override
    public DtoMusteri getMusteriByTckn(String tckn) {

        Musteri musteri = musteriRepository.findByTckn(tckn);
        DtoMusteri dtoMusteri = new DtoMusteri();

        if (musteri == null) {
            throw new BaseException(new ErrorMessage(MessageType.MUSTERİ_BULUNAMADI , musteri.getTckn()));
        }

        BeanUtils.copyProperties(musteri, dtoMusteri);

        return dtoMusteri;
    }


    private String tcknDogrulama(String tckn) {

        String dogrulanacakTckn = tckn;

        if(dogrulanacakTckn.length() != 11){
            throw new BaseException(new ErrorMessage(MessageType.HATALİ_KARAKTER_SAYİSİ , dogrulanacakTckn));
        }

        if(Character.getNumericValue(dogrulanacakTckn.charAt(0)) == 0){
            throw new BaseException(new ErrorMessage(MessageType.İLK_BASAMAK_HATASI , dogrulanacakTckn));
        }

        if(musteriRepository.findByTckn(dogrulanacakTckn) != null){
            throw new BaseException(new ErrorMessage(MessageType.KULLANILMIS_TCKN , dogrulanacakTckn));
        }

        Integer[] tcknDogrulama = new Integer[11];

        for (int i = 0 ; i < 11 ; i++){
            tcknDogrulama[i] = Character.getNumericValue(dogrulanacakTckn.charAt(i));
        }

        Integer tekBasamaklarinToplami = tcknDogrulama[0] + tcknDogrulama[2] + tcknDogrulama[4] + tcknDogrulama[6]  + tcknDogrulama[8];
        Integer ciftBasamaklariToplami = tcknDogrulama[1] + tcknDogrulama[3] + tcknDogrulama[5] + tcknDogrulama[7];
        Integer tekIndeksToplamCarpimi = tekBasamaklarinToplami * 7;
        Integer eldeEdilenSonuc = tekIndeksToplamCarpimi - ciftBasamaklariToplami;

        if(eldeEdilenSonuc % 10 != Character.getNumericValue(dogrulanacakTckn.charAt(9))){
            throw new BaseException(new ErrorMessage(MessageType.TCKN_BASARISIZ ,  dogrulanacakTckn));
        }

        Integer ilk9Basamak = tekBasamaklarinToplami + ciftBasamaklariToplami;
        Integer ilk10Basamak = ilk9Basamak + tcknDogrulama[9];

        if(ilk10Basamak  % 10 != Character.getNumericValue(dogrulanacakTckn.charAt(10))){
            throw new BaseException(new ErrorMessage(MessageType.TCKN_BASARISIZ ,  dogrulanacakTckn));
        }

        return dogrulanacakTckn;
    }

    @Override
    public DtoMusteri saveMusteri(DtoMusteriUI dtoMusteriUI) {

           Musteri musteri = new Musteri();
           DtoMusteri dtoMusteri = new DtoMusteri();

           musteri.setTckn(tcknDogrulama(dtoMusteriUI.getTckn()));
           musteri.setMusteriAd(dtoMusteriUI.getMusteriAd());
           musteri.setMusteriSoyad(dtoMusteriUI.getMusteriSoyad());
           musteri.setEmail(dtoMusteriUI.getEmail());
           musteri.setParola(dtoMusteriUI.getParola());
           musteri.setAdres(dtoMusteriUI.getAdres());
           musteri.setAnneAdi(dtoMusteriUI.getAnneAdi());
           musteri.setBabaAdi(dtoMusteriUI.getBabaAdi());

           if(musteriRepository.findByTckn(musteri.getTckn()) != null){
               throw new BaseException(new ErrorMessage(MessageType.KULLANILMIS_TCKN , musteri.getTckn()));
           }

           if(musteriRepository.findByEmail(musteri.getEmail()) != null){
               throw new BaseException(new ErrorMessage(MessageType.KULLANILMIS_EMAIL , musteri.getEmail()));
           }

           Musteri dbMusteri = musteriRepository.save(musteri);

           BeanUtils.copyProperties(dbMusteri, dtoMusteri);

        return dtoMusteri;
    }

    @Override
    public DtoMusteri updateMusteri(String tckn, DtoMusteriUI dtoMusteriUI) {

        Musteri musteri =  musteriRepository.findByTckn(tckn);
        DtoMusteri dtoMusteri = new DtoMusteri();

        if(musteri == null){
            throw new BaseException(new ErrorMessage(MessageType.MUSTERİ_BULUNAMADI , musteri.getTckn()));
        }

        musteri.setMusteriAd(dtoMusteriUI.getMusteriAd());
        musteri.setMusteriSoyad(dtoMusteriUI.getMusteriSoyad());
        musteri.setEmail(dtoMusteriUI.getEmail());
        musteri.setParola(dtoMusteriUI.getParola());
        musteri.setBabaAdi(dtoMusteriUI.getBabaAdi());
        musteri.setAnneAdi(dtoMusteriUI.getAnneAdi());
        musteri.setAdres(dtoMusteriUI.getAdres());

        Musteri updatedMusteri = musteriRepository.save(musteri);

        BeanUtils.copyProperties(updatedMusteri,dtoMusteri);

        return dtoMusteri;
    }

    @Override
    public void deleteMusteri(String tckn) {

        Musteri musteri =  musteriRepository.findByTckn(tckn);

        if(musteri == null){
            throw new BaseException(new ErrorMessage(MessageType.MUSTERİ_BULUNAMADI , musteri.getTckn()));
        }else{
            musteriRepository.delete(musteri);
        }

    }

    @Override
    public DtoMusteri getMusteriById(int musteriId) {

        Musteri musteri = musteriRepository.findByMusteriId(musteriId);

        if(musteri == null){
            return null;
        }

        DtoMusteri dtoMusteri = new DtoMusteri();
        BeanUtils.copyProperties(musteri, dtoMusteri);

        return dtoMusteri;
    }

}
