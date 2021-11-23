package com.example.jpataken.domain;

import com.example.jpataken.repositories.JpaArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class ArtikelGroepTest {
    private ArtikelGroep groep1, groep2;
    private Artikel artikel1, artikel2;

    @BeforeEach
    void setUp(){
        groep1 = new ArtikelGroep("testGroep1");
        groep2 = new ArtikelGroep("testGroep2");
        artikel1 = new FoodArtikel("testArtikel1", BigDecimal.ONE, BigDecimal.TEN,7, groep1);
    }

    @Test
    void getArtikelInArtikelGroep(){
        //assertThat(artikel1.getArtikelGroep()).isEqualTo(groep1);
        assertThat(groep1.getArtikels()).containsOnly(artikel1);
    }
    @Test
    void artikelToevoegen(){
        assertThat(groep2.addArtikel(artikel1)).isTrue();
        assertThat(groep1.getArtikels()).doesNotContain(artikel1);
        assertThat(groep2.getArtikels()).containsOnly(artikel1);
    }
    @Test
    void nullArtikelToevoegenLuktNiet(){
        assertThatNullPointerException().isThrownBy(()-> groep1.addArtikel(null));
    }

}