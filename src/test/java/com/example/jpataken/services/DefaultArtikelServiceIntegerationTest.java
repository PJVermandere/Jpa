package com.example.jpataken.services;

import com.example.jpataken.exceptions.ArtikelNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJpaTest(showSql = false)
@Import(DefaultArtikelService.class)
@ComponentScan(value = "com.example.jpataken.repositories", resourcePattern = "JpaArtikelRepository.class")
@Sql("/ArtikelRepositoryTest.sql")
class DefaultArtikelServiceIntegerationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final DefaultArtikelService service;
    private final EntityManager manager;

    DefaultArtikelServiceIntegerationTest(DefaultArtikelService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }
    long idVanTestArtikel(){
       return jdbcTemplate.queryForObject("select id from artikels where naam = 'Test'", Long.class);
    }

    @Test
    void verhoogVerkoopprijs() {
        service.verhoogVerkoopprijs(idVanTestArtikel(), BigDecimal.ONE);
        manager.flush();
        assertThat(countRowsInTableWhere("artikels", "naam='Test' and verkoopprijs=2.2")).isOne();
    }
    @Test
    void verhoogOnbestaandArtikel(){
        assertThatExceptionOfType(ArtikelNietGevondenException.class).isThrownBy(() -> service.verhoogVerkoopprijs(-1, BigDecimal.ONE));

    }
}