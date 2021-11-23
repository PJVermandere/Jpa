package com.example.jpataken.repositories;

import com.example.jpataken.domain.Artikel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository {
    Optional<Artikel> findById(long id);
    void persistNew(Artikel artikel);
    List<Artikel> findByWord(String word);
    int algemenePrijsVerhoging(BigDecimal opslagPercentage);
}
