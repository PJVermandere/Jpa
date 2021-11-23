package com.example.jpataken.services;

import com.example.jpataken.domain.Artikel;
import com.example.jpataken.domain.ArtikelGroep;
import com.example.jpataken.domain.FoodArtikel;
import com.example.jpataken.exceptions.ArtikelNietGevondenException;
import com.example.jpataken.repositories.ArtikelRepository;
import com.example.jpataken.repositories.JpaArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultArtikelServiceTest {
    private DefaultArtikelService service;
    @Mock
    private ArtikelRepository repository;
    private Artikel artikel;



    @BeforeEach
    void setUp() {
        this.service = new DefaultArtikelService(repository);
        artikel = new FoodArtikel("Test", BigDecimal.ONE, BigDecimal.valueOf(2), 1, new ArtikelGroep("testGroep"));

    }

    @Test
    void verhoogVerkoopprijs() {
        when(repository.findById(1)).thenReturn(Optional.of(artikel));
        service.verhoogVerkoopprijs(1,BigDecimal.ONE);
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo("3");
        verify(repository).findById(1);
    }
    @Test
    void verhoogVerkoopprijsOnbestaandArtikel(){
        assertThatExceptionOfType(ArtikelNietGevondenException.class).isThrownBy(() -> service.verhoogVerkoopprijs(-1, BigDecimal.ONE));
        verify(repository).findById(-1);
    }
}