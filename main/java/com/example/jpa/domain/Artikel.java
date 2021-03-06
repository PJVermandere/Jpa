package com.example.jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "artikels")
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal aankoopprijs, verkoopprijs;

    protected Artikel() {
    }
    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs){
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
    }
    public String getNaam() {
        return naam;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }

    public void verhoogVerkoopprijs(BigDecimal bedrag){
        if(bedrag.compareTo(BigDecimal.ZERO) == 1){
            verkoopprijs = verkoopprijs.add(bedrag);
        }
    }
}
