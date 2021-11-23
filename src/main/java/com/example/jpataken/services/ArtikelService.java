package com.example.jpataken.services;

import java.math.BigDecimal;

public interface ArtikelService {
    void verhoogVerkoopprijs(long id, BigDecimal bedrag);
}
