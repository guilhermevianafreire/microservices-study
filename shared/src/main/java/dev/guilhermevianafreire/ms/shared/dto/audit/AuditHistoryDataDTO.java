package dev.guilhermevianafreire.ms.shared.dto.audit;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record AuditHistoryDataDTO(BigDecimal id, long version, AuditHistoryType type, String author,
                                  LocalDateTime dateTime, Instant dateTimeNoTimeZone, Map<String, String> properties,
                                  Map<String, Object> data, List<String> changedData) {
}
