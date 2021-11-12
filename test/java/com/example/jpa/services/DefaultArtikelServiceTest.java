package com.example.jpa.services;

import com.example.jpa.repositories.JpaArtikelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DefaultArtikelService.class)
@ComponentScan(value = "com.example.jpa.repositories",resourcePattern = "JpaArtikelRepository.class")
@Sql("/ArtikelRepositorySql.sql")
class DefaultArtikelServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final DefaultArtikelService service;
    private final EntityManager manager;

    DefaultArtikelServiceTest(DefaultArtikelService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }

    long getId() {
        return jdbcTemplate.queryForObject("select id from artikels where naam = 'kruiden'", Long.class);
    }
    @Test
    void verhoogVerkoopprijs(){
        service.verhoogPrijs(getId(), BigDecimal.ONE);
        manager.flush();
        assertThat(jdbcTemplate.queryForObject("select verkoopprijs from artikels where id = ?", BigDecimal.class, getId())).isEqualByComparingTo("2.5");
    }
}