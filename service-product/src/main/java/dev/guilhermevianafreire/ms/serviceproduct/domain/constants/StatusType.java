package dev.guilhermevianafreire.ms.serviceproduct.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusType {

    INACTIVE(0),
    ACTIVE(1);

    private final Integer code;

    public static StatusType lookupByCode(Integer code) {
        return Stream.of(StatusType.values()).filter(constant -> constant.getCode().equals(code)).findFirst().orElseThrow();
    }

}
