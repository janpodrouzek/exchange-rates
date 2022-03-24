package cz.janpodrouzek.exchangerates.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDto {
    private String shortName;
    private Timestamp validFrom;
    private String name;
    private Integer amount;
    private BigDecimal valBuy;
    private BigDecimal valSell;
    private BigDecimal valMid;
    private BigDecimal currBuy;
    private BigDecimal currSell;
    private BigDecimal currMid;
    private BigDecimal move;
    private BigDecimal cnbMid;
    private Integer version;
}
