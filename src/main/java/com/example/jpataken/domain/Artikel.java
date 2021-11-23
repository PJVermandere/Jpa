package com.example.jpataken.domain;

import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artikels")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
@NamedEntityGraph(name = Artikel.MET_ARTIKELGROEP, attributeNodes = @NamedAttributeNode("artikelGroep"))
public abstract class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal aankoopprijs, verkoopprijs;
    @ElementCollection
    @CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelId"))
    private Set<Korting> kortingen;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artikelgroepId")
    private ArtikelGroep artikelGroep;
    public static final String MET_ARTIKELGROEP = "Docent.metArtikelGroep";

    protected Artikel() {}

    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, ArtikelGroep artikelGroep) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.kortingen = new LinkedHashSet<>();
       setArtikelGroep(artikelGroep);
    }

    public long getId() {
        return id;
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
        verkoopprijs = verkoopprijs.add(bedrag);
    }

    public Set<Korting> getKortingen() {
        return Collections.unmodifiableSet(kortingen);
    }

    public ArtikelGroep getArtikelGroep() {
        return artikelGroep;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Artikel artikel && Objects.equals(naam, artikel.naam) && Objects.equals(aankoopprijs, artikel.aankoopprijs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, aankoopprijs);
    }
    public void setArtikelGroep(ArtikelGroep artikelGroep){
        if(!artikelGroep.getArtikels().contains(this)){
            artikelGroep.addArtikel(this);
        }
        this.artikelGroep = artikelGroep;
    }
}
