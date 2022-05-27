package dev.guilhermevianafreire.ms.shared.dto.audit;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record AuditChangeDataDTO(BigDecimal id, String author, LocalDateTime dateTime, Instant dateTimeNoTimeZone,
                                 AuditChangeType changeType, String propertyName,
                                 AuditChangePropertyType changePropertyType, Object oldValue, Object newValue) {
}
