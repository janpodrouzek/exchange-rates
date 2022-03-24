package cz.janpodrouzek.exchangerates.repository;

import cz.janpodrouzek.exchangerates.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
    List<ExchangeRateEntity> findDistinctByOrderByCreatedAt();
}
