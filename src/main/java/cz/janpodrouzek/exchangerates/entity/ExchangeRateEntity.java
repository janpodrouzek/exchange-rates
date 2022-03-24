package cz.janpodrouzek.exchangerates.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
public class ExchangeRateEntity
{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer amount;

    @Column
    private Timestamp validFrom;

    @Column(precision = 7, scale = 3)
    private BigDecimal valBuy;

    @Column(precision = 7, scale = 3)
    private BigDecimal valSell;

    @Column(precision = 7, scale = 3)
    private BigDecimal valMid;

    @Column(precision = 7, scale = 3)
    private BigDecimal currBuy;

    @Column(precision = 7, scale = 3)
    private BigDecimal currSell;

    @Column(precision = 7, scale = 3)
    private BigDecimal currMid;

    @Column(precision = 7, scale = 3)
    private BigDecimal cnbMid;

    @Column(precision = 5, scale = 2)
    private BigDecimal move;

    @Column
    private Integer version;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id", nullable = false)
    private CurrencyEntity currency;
}
