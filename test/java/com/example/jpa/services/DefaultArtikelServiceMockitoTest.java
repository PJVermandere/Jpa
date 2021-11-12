package com.example.jpa.services;

import com.example.jpa.domain.Artikel;
import com.example.jpa.repositories.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultArtikelServiceMockitoTest {
    private Artikel artikel;
    private DefaultArtikelService service;
    @Mock
    private ArtikelRepository repository;
    @BeforeEach
    void setUp() {
        service = new DefaultArtikelService(repository);
        artikel = new Artikel("Ajuinn", BigDecimal.valueOf(0.53), BigDecimal.ONE);
    }
    @Test
    void verhoogPrijs(){
        when(repository.findById(1)).thenReturn(Optional.of(artikel));
        service.verhoogPrijs(1, BigDecimal.ONE);
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo("2");
        verify(repository).findById(1);
    }
}