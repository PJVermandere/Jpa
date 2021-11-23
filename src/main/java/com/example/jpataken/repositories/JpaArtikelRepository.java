package com.example.jpataken.repositories;

import com.example.jpataken.domain.Artikel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaArtikelRepository implements ArtikelRepository {
    private final EntityManager manager;

    public JpaArtikelRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Artikel> findById(long id) {
        return Optional.ofNullable(manager.find(Artikel.class, id));
    }

    @Override
    public void persistNew(Artikel artikel) {
        manager.persist(artikel);
    }

    @Override
    public List<Artikel> findByWord(String word) {
        return manager.createNamedQuery("Artikel.findByWord", Artikel.class)
                .setParameter("word", word)
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph(Artikel.MET_ARTIKELGROEP))
                .getResultList();

    }

    @Override
    public int algemenePrijsVerhoging(BigDecimal opslagPercentage) {
        return manager.createNamedQuery("Artikel.algemenePrijsVerhoging").setParameter("percentage", opslagPercentage.intValue()).executeUpdate();
    }
}
