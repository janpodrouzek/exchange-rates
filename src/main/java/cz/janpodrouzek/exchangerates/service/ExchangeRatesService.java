package cz.janpodrouzek.exchangerates.service;

import cz.janpodrouzek.exchangerates.dto.ExchangeRateDto;
import cz.janpodrouzek.exchangerates.entity.CurrencyEntity;
import cz.janpodrouzek.exchangerates.provider.ExchangeRatesProvider;
import cz.janpodrouzek.exchangerates.repository.CurrencyRepository;
import cz.janpodrouzek.exchangerates.repository.ExchangeRateRepository;
import cz.janpodrouzek.exchangerates.transformer.ExchangeRateTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeRatesService {

    private final ExchangeRatesProvider provider;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    public Flux<ExchangeRateDto> getRates(boolean useDb) {
        return useDb ? fetchFromDatabase() : fetchFromApi();
    }

    public Flux<ExchangeRateDto> fetchFromDatabase() {
        return Flux.fromIterable(exchangeRateRepository.findDistinctByOrderByCreatedAt()) // JPA doesn't support reactive repositories :(
                .map(ExchangeRateTransformer::toDto);
    }

    public Flux<ExchangeRateDto> fetchFromApi() {
        return provider.getRates()
                .map(exchangeRateDto -> {
                    Optional<CurrencyEntity> currencyOptional = currencyRepository.findByShortName(exchangeRateDto.getShortName());
                    CurrencyEntity currency;
                    if (currencyOptional.isEmpty()) {
                        currency = createCurrency(exchangeRateDto);
                    } else {
                        currency = currencyOptional.get();
                    }

                    return exchangeRateRepository.save(ExchangeRateTransformer.toEntity(exchangeRateDto, currency));
                }).map(ExchangeRateTransformer::toDto);
    }

    private CurrencyEntity createCurrency(ExchangeRateDto dto) {
        return currencyRepository.save(ExchangeRateTransformer.currencyToEntity(dto));
    }
}
