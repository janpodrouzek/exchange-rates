package cz.janpodrouzek.exchangerates.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class CurrencyEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String shortName;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    private Set<ExchangeRateEntity> exchangeRates = new HashSet<>();
}

