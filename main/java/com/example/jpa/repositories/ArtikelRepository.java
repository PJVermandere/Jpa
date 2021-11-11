package com.example.jpa.repositories;

import com.example.jpa.domain.Artikel;

import java.util.Optional;

public interface ArtikelRepository {
    Optional<Artikel> findById(long id);
    void persist(Artikel artikel);
}
