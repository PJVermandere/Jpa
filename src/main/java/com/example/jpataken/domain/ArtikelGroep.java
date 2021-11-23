package com.example.jpataken.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artikelGroep")
    @OrderBy(value = "naam")
    private Set<Artikel> artikels;

    public ArtikelGroep(String naam) {
        this.naam = naam;
        this.artikels = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Artikel> getArtikels() {
        return artikels;
    }

    public boolean addArtikel(Artikel artikel){
        var toegevoegd = artikels.add(artikel);
        var huidigeArtikelGroepVanArtikel = artikel.getArtikelGroep();
        if(huidigeArtikelGroepVanArtikel!=null && huidigeArtikelGroepVanArtikel != this){
            huidigeArtikelGroepVanArtikel.artikels.remove(artikel);
        }
        if(this != huidigeArtikelGroepVanArtikel){
            artikel.setArtikelGroep(this);
        }
        return toegevoegd;
    }

    protected ArtikelGroep() {
    }
}
