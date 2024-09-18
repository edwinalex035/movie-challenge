package com.challenge.moviecatalog.service;

import lombok.Data;

@Data
public class RateResponse {
    private Long id;

    private int rate;

    private long movieId;

    private String movieName;

    private long createdBy;
}
