package com.example.jpa.services;


import java.math.BigDecimal;

public interface ArtikelService {
    void verhoogPrijs(long id, BigDecimal bedrag);
}
