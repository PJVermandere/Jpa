package com.example.jpa.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArtikelTest {
    private Artikel artikel;
    @BeforeEach
    void setUp() {
        artikel = new Artikel("brood", BigDecimal.ONE, BigDecimal.valueOf(1.63));
    }

    @Test
    void verhoogVerkoopprijs() {
        artikel.verhoogVerkoopprijs(BigDecimal.valueOf(0.50));
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo("2.13");
    }
    @Test
    void verhoogMetNegatief(){
        artikel.verhoogVerkoopprijs(BigDecimal.valueOf(-0.53));
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo("1.63");
    }
}