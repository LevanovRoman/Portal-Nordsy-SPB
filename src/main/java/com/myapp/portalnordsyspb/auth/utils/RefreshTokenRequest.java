package com.myapp.portalnordsyspb.auth.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {

    @NotBlank(message = "Please enter refresh token value!")
    private String refreshToken;
}
