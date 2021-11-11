package com.example.jpa.repositories;

import com.example.jpa.domain.Artikel;
import com.example.jpa.exceptions.ArtikelNietGevondenException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaArtikelRepository implements ArtikelRepository {
    private final EntityManager manager;

    public JpaArtikelRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Artikel> findById(long id) {
        return Optional.ofNullable(manager.find(Artikel.class, id));
    }
    @Override
    public void persist(Artikel artikel){
        manager.persist(artikel);
    }
}
