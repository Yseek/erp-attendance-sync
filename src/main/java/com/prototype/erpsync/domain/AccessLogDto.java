package com.prototype.erpsync.domain;

import java.time.LocalDateTime;

public record AccessLogDto(
        Long id,
        String employeeId,
        LocalDateTime timestamp,
        String entryType
) {
}
