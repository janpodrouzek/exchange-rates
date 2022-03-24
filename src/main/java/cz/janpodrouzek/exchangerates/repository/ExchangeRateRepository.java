package cz.janpodrouzek.exchangerates.repository;

import cz.janpodrouzek.exchangerates.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
    @Query(value = "SELECT * FROM exchange_rate WHERE id IN (SELECT id FROM " +
            "(SELECT max(exchange_rate.id) as id, c.short_name FROM exchange_rate LEFT JOIN currency c on exchange_rate.currency_id = c.id\n" +
            "GROUP BY c.short_name) as isn)", nativeQuery = true)
    List<ExchangeRateEntity> findDistinctByOrderByCreatedAt();
}
