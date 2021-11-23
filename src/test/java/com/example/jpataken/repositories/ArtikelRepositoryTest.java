package com.example.jpataken.repositories;

import com.example.jpataken.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Import(JpaArtikelRepository.class)
@Sql({"/ArtikelGroepTest.sql","/ArtikelRepositoryTest.sql"})
class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String artikelsTable = "artikels";
    private final JpaArtikelRepository repository;
    private final EntityManager manager;


    ArtikelRepositoryTest(JpaArtikelRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    long idVanTestNonFood(){
        return jdbcTemplate.queryForObject("select id from artikels where naam='Test'", Long.class);
    }

    long idVanTestFood(){
        return jdbcTemplate.queryForObject("select id from artikels where naam='Test1'", Long.class);
    }



    @Test
    void findByIdNonFoodArtikel() {
        assertThat(repository.findById(idVanTestNonFood()))
                .containsInstanceOf(NonFoodArtikel.class)
                .hasValueSatisfying(artikel -> assertThat(artikel.getNaam()).isEqualTo("Test"));
    }

    @Test
    void findByIdFoodArtikel() {
        assertThat(repository.findById(idVanTestFood()))
                .containsInstanceOf(FoodArtikel.class)
                .hasValueSatisfying(artikel -> assertThat(artikel.getNaam()).isEqualTo("Test1"));
    }

    @Test
    void findByWrongId(){
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void persistNewFoodArtikel(){
        var groep = new ArtikelGroep("testGroep");
        manager.persist(groep);
        repository.persistNew(new FoodArtikel("foodTest", BigDecimal.ONE, BigDecimal.TEN, 7, groep));
        assertThat(countRowsInTableWhere(artikelsTable, "naam='foodTest'")).isOne();
    }
    @Test
    void persistNewNonFoodArtikel(){
        var groep = new ArtikelGroep("testGroep1");
        manager.persist(groep);
        var artikel = new NonFoodArtikel("nonFoodTest", BigDecimal.ONE, BigDecimal.TEN, 5, groep);
        repository.persistNew(artikel);
        assertThat(countRowsInTableWhere(artikelsTable, "naam='nonFoodTest'")).isOne();
    }
    @Test
    void findArtikelsByWordInName(){
        var artikels = repository.findByWord("Test");
        manager.clear();
        assertThat(artikels)
                .hasSize(countRowsInTableWhere(artikelsTable, "naam like '%Test%'"))
                .extracting(Artikel::getNaam)
                .allSatisfy(naam -> assertThat(naam).containsIgnoringCase("test"))
                .isSortedAccordingTo(String::compareToIgnoreCase);
        assertThat(artikels)
                .extracting(Artikel::getArtikelGroep)
                .extracting(ArtikelGroep::getNaam);
    }
    @Test
    void algemenePrijsVerhoging(){
        assertThat(repository.algemenePrijsVerhoging(BigDecimal.TEN)).isEqualTo(countRowsInTable(artikelsTable));
        assertThat(countRowsInTableWhere(artikelsTable, "naam='Test2' and verkoopprijs=2.20")).isOne();
    }
    @Test
    void getKorting(){
        assertThat(repository.findById(idVanTestFood()))
                .hasValueSatisfying(artikel -> assertThat(artikel.getKortingen())
                        .containsOnly(new Korting(5, BigDecimal.TEN)));
    }
    @Test
    void artikelGroepIsLazyLoaded(){
        assertThat(repository.findById(idVanTestFood()))
                .hasValueSatisfying(
                        artikel -> assertThat(artikel.getArtikelGroep().getNaam())
                                .isEqualTo("testGroep1"));
    }
}