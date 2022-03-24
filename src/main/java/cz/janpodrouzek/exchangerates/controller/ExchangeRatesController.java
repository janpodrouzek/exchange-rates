package cz.janpodrouzek.exchangerates.controller;

import cz.janpodrouzek.exchangerates.dto.ExchangeRateDto;
import cz.janpodrouzek.exchangerates.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/rates/exchangerates")
@AllArgsConstructor
public class ExchangeRatesController {

    private final ExchangeRatesService service;

    @CrossOrigin
    @GetMapping
    public Flux<ExchangeRateDto> index(@RequestParam(name = "usedb", required = false, defaultValue = "true") boolean useDb) {
        return service.getRates(useDb);
    }

}
