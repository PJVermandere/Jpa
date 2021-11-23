package com.example.jpataken.services;

import com.example.jpataken.exceptions.ArtikelNietGevondenException;
import com.example.jpataken.repositories.ArtikelRepository;
import com.example.jpataken.repositories.JpaArtikelRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class DefaultArtikelService implements ArtikelService {
    private final ArtikelRepository repository;

    public DefaultArtikelService(ArtikelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void verhoogVerkoopprijs(long id, BigDecimal bedrag) {
        repository.findById(id).orElseThrow(ArtikelNietGevondenException::new).verhoogVerkoopprijs(bedrag);
    }
}
