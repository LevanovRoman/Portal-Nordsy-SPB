package com.myapp.portalnordsyspb.level5S.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public record Result5STableDto(
        String month,
        String criterion,
        int value
) {
}
