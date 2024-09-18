package com.challenge.moviecatalog.web.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorMessage implements Serializable {

    private static final String EMPTY_MESSAGE = "Empty message";

    private String message;

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ErrorMessage() {
        super();
    }

    public ErrorMessage(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        if (ObjectUtils.isEmpty(message)) {
            message = EMPTY_MESSAGE;
        }
        return message;
    }
}