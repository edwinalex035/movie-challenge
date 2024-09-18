package com.challenge.moviecatalog.service;

import com.challenge.moviecatalog.model.domain.Movie;
import com.challenge.moviecatalog.model.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> list(int page, int size, String orderBy, String direction) {
        Pageable pageableRequest;
        if (ObjectUtils.isEmpty(orderBy)) {
            pageableRequest = PageRequest.of(page, size);
        } else {
            pageableRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), orderBy);
        }
        return movieRepository.findAll(pageableRequest);
    }

    public Optional<Movie> getById(long id) {
        return movieRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(long id, Movie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }

    public void delete(long id) {
        movieRepository.deleteById(id);
    }
}
