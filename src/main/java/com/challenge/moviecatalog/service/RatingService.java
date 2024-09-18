package com.challenge.moviecatalog.service;

import com.challenge.moviecatalog.model.domain.Movie;
import com.challenge.moviecatalog.model.domain.Rate;
import com.challenge.moviecatalog.model.domain.User;
import com.challenge.moviecatalog.model.repository.RateRepository;
import com.challenge.moviecatalog.web.controller.exceptions.ResourceNotFoundException;
import com.challenge.moviecatalog.web.requests.RateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RateRepository rateRepository;

    private final MovieService movieService;

    public Optional<RateResponse> getById(long id) {
        Optional<Rate> rate = rateRepository.findById(id);
        return rate.map(this::convertToDto);
    }

    public List<RateResponse> list() {
        return rateRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RateResponse createRate(RateRequest rateRequest, User user) {
        Rate rate = convertToDomain(rateRequest, user);

        return convertToDto(rateRepository.save(rate));

    }

    public RateResponse updateRate(long id, RateRequest rateRequest, User user) {

        Rate rate = convertToDomain(rateRequest, user);
        rate.setId(id);

        return convertToDto(rateRepository.save(rate));

    }

    public void delete(long id) {
        rateRepository.deleteById(id);
    }

    private Rate convertToDomain(RateRequest rateRequest, User user) {
        Movie movie = movieService.getById(rateRequest.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Rate rate = new Rate();
        rate.setRate(rateRequest.getRate());
        rate.setMovie(movie);
        rate.setCreatedBy(user.getId());

        return rate;
    }

    private RateResponse convertToDto(Rate rate) {
        RateResponse rateResponse = new RateResponse();
        rateResponse.setRate(rate.getRate());
        rateResponse.setId(rate.getId());
        rateResponse.setMovieId(rate.getMovie().getId());
        rateResponse.setMovieName(rate.getMovie().getName());
        rateResponse.setCreatedBy(rate.getCreatedBy());

        return rateResponse;
    }


}
