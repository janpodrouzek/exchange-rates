package cz.janpodrouzek.exchangerates.provider;


import cz.janpodrouzek.exchangerates.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ExchangeRatesProvider {

    private final String apiKey;
    private final String baseUrl = "https://webapi.developers.erstegroup.com";
    private final WebClient webClient;

    public ExchangeRatesProvider(@Value("${API_KEY}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.create(baseUrl);
    }

    public Flux<ExchangeRateDto> getRates() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/csas/public/sandbox/v2/rates/exchangerates")
                        .queryParam("web-api-key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToFlux(ExchangeRateDto.class);
    }
}
