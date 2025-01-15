package com.myapp.portalnordsyspb.sendMail.dto;

public record MailResponseDto(
        String tabNumber,
        String fullName,
        String department
) {
}
