package com.myapp.portalnordsyspb.sendMail.dto;

public record MailRequestDto(
        String tabNumber,
        long directionId
) {
}
