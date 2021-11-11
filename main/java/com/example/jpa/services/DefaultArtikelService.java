package com.example.jpa.services;

import com.example.jpa.exceptions.ArtikelNietGevondenException;
import com.example.jpa.repositories.ArtikelRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultArtikelService implements ArtikelService {
    private final ArtikelRepository repository;

    public DefaultArtikelService(ArtikelRepository repository) {
        this.repository = repository;
    }


    @Override
    public void verhoogPrijs(long id, BigDecimal bedrag) {
        repository.findById(id).orElseThrow(ArtikelNietGevondenException::new).verhoogVerkoopprijs(bedrag);
    }
}
