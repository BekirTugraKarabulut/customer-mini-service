package com.example.repository;

import com.example.entity.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, String> {

    Musteri findByTckn(String tckn);
    Musteri findByEmail(String email);
    Musteri findByMusteriId(int musteriId);

}
