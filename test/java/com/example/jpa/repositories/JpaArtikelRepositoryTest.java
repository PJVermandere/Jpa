package com.example.jpa.repositories;

import com.example.jpa.domain.Artikel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaArtikelRepository.class)
@Sql("/ArtikelRepositorySql.sql")
class JpaArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaArtikelRepository repository;
    private final Artikel artikel;
    JpaArtikelRepositoryTest(JpaArtikelRepository repository) {
        this.repository = repository;
        artikel = new Artikel("groenten", BigDecimal.ONE, BigDecimal.valueOf(2));
    }
    private long getIdVan(String naam){
       return jdbcTemplate.queryForObject("select id from artikels where naam=?",Long.class, naam);
    }
    @Test
    void findById() {
        assertThat(repository.findById(getIdVan("kruiden"))).hasValueSatisfying(artikel -> assertThat(artikel.getNaam()).isEqualTo("kruiden"));
    }
    @Test
    void findByWrongId(){
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    void persistArtikel(){
        repository.persist(artikel);
        assertThat(jdbcTemplate.queryForObject("select id from artikels where naam='groenten'", Long.class)).isPositive();
    }
}