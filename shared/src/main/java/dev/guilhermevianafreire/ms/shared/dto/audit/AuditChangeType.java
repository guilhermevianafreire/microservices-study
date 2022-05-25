package dev.guilhermevianafreire.ms.shared.dto.audit;

import java.util.stream.Stream;

public enum AuditChangeType {
    NEW_OBJECT("NewObject"),
    INITIAL_VALUE_CHANGE("InitialValueChange"),
    VALUE_CHANGE("ValueChange");

    private final String changeTypeName;

    AuditChangeType(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public static AuditChangeType lookupByChangeTypeName(String changeTypeName) {
        return Stream.of(AuditChangeType.values()).filter(constant -> constant.getChangeTypeName().equals(changeTypeName)).findFirst().orElseThrow();
    }
}
