package com.challenge.moviecatalog.web.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequest {
    @Email(message = "email required")
    private String email;
    @NotBlank(message = "Password must be fill")
    private String password;
    @NotBlank(message = "Name must be fill")
    private String name;
    @NotNull(message = "Role must be ADMIN or USER")
    private RoleType role;
}
