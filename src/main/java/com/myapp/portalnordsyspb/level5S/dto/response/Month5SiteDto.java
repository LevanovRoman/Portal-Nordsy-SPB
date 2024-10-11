package com.myapp.portalnordsyspb.level5S.dto.response;

import java.util.List;

public record Month5SiteDto(

        String month,
        List<Area5SiteDto> areas
) {
}
