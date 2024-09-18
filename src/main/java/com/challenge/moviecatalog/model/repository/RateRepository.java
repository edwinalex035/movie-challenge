package com.challenge.moviecatalog.model.repository;

import com.challenge.moviecatalog.model.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
