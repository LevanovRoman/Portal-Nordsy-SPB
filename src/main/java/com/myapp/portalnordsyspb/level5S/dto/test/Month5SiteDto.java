package com.myapp.portalnordsyspb.level5S.dto.test;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public record Month5SiteDto(

        String month,
        List<Area5SiteDto> areas
) {
}
