package com.myapp.portalnordsyspb.auth.dto;

public record UserResponseDto(
        String name,
        String email,
        boolean isAuth,
        int counterVisit
) {
}
