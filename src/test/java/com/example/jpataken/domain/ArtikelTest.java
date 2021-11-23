package com.example.jpataken.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

class ArtikelTest {
    private Artikel artikel1;
    private ArtikelGroep artikelGroep1, artikelGroep2;
    @BeforeEach
    void setUp() {
        artikelGroep1 = new ArtikelGroep("testGroep");
        artikelGroep2 = new ArtikelGroep("testGroep2");
        artikel1 = new FoodArtikel("Test", BigDecimal.ONE, BigDecimal.valueOf(2), 1, artikelGroep1);
    }

    @Test
    void verhoogVerkoopprijs() {
        artikel1.verhoogVerkoopprijs(BigDecimal.ONE);
        assertThat(artikel1.getVerkoopprijs()).isEqualByComparingTo("3");
    }
    @Test
    void artikel1HeeftGroep1AlsArtikelGroep(){
        assertThat(artikel1.getArtikelGroep()).isEqualTo(artikelGroep1);
    }
    @Test
    void artikelVeranderenVanGroep(){
        assertThat(artikel1.getArtikelGroep()).isEqualTo(artikelGroep1);
        artikel1.setArtikelGroep(artikelGroep2);
        assertThat(artikel1.getArtikelGroep()).isEqualTo(artikelGroep2);
        assertThat(artikelGroep1.getArtikels()).doesNotContain(artikel1);
        assertThat(artikelGroep2.getArtikels()).containsOnly(artikel1);
    }
    @Test
    void veranderenNaarGroepNull() {
        assertThatNullPointerException().isThrownBy(()->artikel1.setArtikelGroep(null));
    }
    @Test
    void nullGroepInConstructorException(){
        assertThatNullPointerException().isThrownBy(() -> new FoodArtikel("test", BigDecimal.ONE, BigDecimal.TEN, 7, null));
    }
}