package cz.janpodrouzek.exchangerates.transformer;

import cz.janpodrouzek.exchangerates.dto.ExchangeRateDto;
import cz.janpodrouzek.exchangerates.entity.CurrencyEntity;
import cz.janpodrouzek.exchangerates.entity.ExchangeRateEntity;

public class ExchangeRateTransformer {
    public static ExchangeRateEntity toEntity(ExchangeRateDto dto, CurrencyEntity currencyEntity) {
        ExchangeRateEntity entity = new ExchangeRateEntity();
        entity.setAmount(dto.getAmount());
        entity.setVersion(dto.getVersion());
        entity.setCnbMid(dto.getCnbMid());
        entity.setCurrBuy(dto.getCurrBuy());
        entity.setCurrSell(dto.getCurrSell());
        entity.setCurrMid(dto.getCurrMid());
        entity.setValBuy(dto.getValBuy());
        entity.setValSell(dto.getValSell());
        entity.setValMid(dto.getValMid());
        entity.setMove(dto.getMove());
        entity.setValidFrom(dto.getValidFrom());
        entity.setCurrency(currencyEntity);

        return entity;
    }

    public static ExchangeRateDto toDto(ExchangeRateEntity entity) {
        CurrencyEntity currency = entity.getCurrency();

        return ExchangeRateDto.builder()
            .shortName(currency.getShortName())
            .name(currency.getName())
            .amount(entity.getAmount())
            .version(entity.getVersion())
            .cnbMid(entity.getCnbMid())
            .currBuy(entity.getCurrBuy())
            .currSell(entity.getCurrSell())
            .currMid(entity.getCurrMid())
            .valBuy(entity.getValBuy())
            .valSell(entity.getValSell())
            .valMid(entity.getValMid())
            .move(entity.getMove())
            .validFrom(entity.getValidFrom())
            .build();
    }

    public static CurrencyEntity currencyToEntity(ExchangeRateDto dto) {
        CurrencyEntity entity = new CurrencyEntity();
        entity.setName(dto.getName());
        entity.setShortName(dto.getShortName());

        return entity;
    }
}
