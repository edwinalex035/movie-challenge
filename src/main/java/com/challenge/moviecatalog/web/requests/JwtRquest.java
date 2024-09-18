package com.challenge.moviecatalog.web.requests;

import lombok.Data;

@Data
public class JwtRquest {
    private String username;

    private String password;
}
