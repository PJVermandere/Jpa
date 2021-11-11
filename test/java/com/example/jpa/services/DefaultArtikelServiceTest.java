package com.example.jpa.services;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(DefaultArtikelService.class)
@ComponentScan(resourcePattern = "repositories", c)
class DefaultArtikelServiceTest {

    @BeforeEach
    void setUp() {
    }
}