package com.challenge.moviecatalog.web.requests;

import lombok.Data;

@Data
public class RateRequest {
    private long rateId;

    private long movieId;

    private int rate;


}
