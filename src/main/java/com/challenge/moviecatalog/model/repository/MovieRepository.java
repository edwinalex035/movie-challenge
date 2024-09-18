package com.challenge.moviecatalog.model.repository;

import com.challenge.moviecatalog.model.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
