package com.challenge.moviecatalog.web.controller;

import com.challenge.moviecatalog.model.domain.User;
import com.challenge.moviecatalog.web.requests.RateRequest;
import com.challenge.moviecatalog.service.RateResponse;
import com.challenge.moviecatalog.service.RatingService;
import com.challenge.moviecatalog.web.controller.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RateResponse>> list() {
        return ResponseEntity.ok(ratingService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(ratingService.getById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rating not found")));
    }

    @PostMapping
    public ResponseEntity<RateResponse> create(@Valid @RequestBody RateRequest rateRequest,
                               @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ratingService.createRate(rateRequest, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateResponse> update(@PathVariable long id, @Valid @RequestBody RateRequest rateRequest,
                               @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ratingService.updateRate(id, rateRequest, user));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        ratingService.delete(id);
    }

}
