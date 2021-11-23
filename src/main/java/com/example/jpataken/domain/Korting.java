package com.example.jpataken.domain;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@AccessType(value = AccessType.Type.FIELD)
public class Korting {
    private int vanafAantal;
    private BigDecimal percentage;

    public Korting(int vanafAantal, BigDecimal percentage) {
        this.vanafAantal = vanafAantal;
        this.percentage = percentage;
    }

    public Korting() {

    }


    @Override
    public boolean equals(Object o) {
        return o instanceof Korting korting && vanafAantal == korting.vanafAantal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vanafAantal);
    }
}
