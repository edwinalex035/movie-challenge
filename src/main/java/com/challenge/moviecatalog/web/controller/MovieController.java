package com.challenge.moviecatalog.web.controller;

import com.challenge.moviecatalog.model.domain.Movie;
import com.challenge.moviecatalog.service.MovieService;
import com.challenge.moviecatalog.web.controller.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping(params = { "page", "size", "orderBy", "direction"})
    public ResponseEntity<Page<Movie>> listMovies(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                  @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                  @RequestParam(name = "orderBy", required = false) String orderBy,
                                                  @RequestParam(name ="direction", required = false) String direction) {
        return ResponseEntity.ok(movieService.list(page, size, orderBy, direction));
    }

    @GetMapping("/{id}")

    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        return ResponseEntity.ok(movieService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found")));

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.update(id, movie));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable long id) {
        movieService.delete(id);
    }


}
