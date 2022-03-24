package cz.janpodrouzek.exchangerates.repository;

import cz.janpodrouzek.exchangerates.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    Optional<CurrencyEntity> findByShortName(String shortName);
}
