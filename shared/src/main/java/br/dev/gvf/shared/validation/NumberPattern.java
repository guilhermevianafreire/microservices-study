package br.dev.gvf.shared.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NumberPattern {
    /**
     * Pattern: "[-+]?\d+([.,]\d+)?"
     */
    REGEX_NUMBERS_GENERAL("[-+]?\\d+([.,]\\d+)?"),
    /**
     * Pattern: "[-+]?\d+(\.\d+)?"
     */
    REGEX_NUMBERS_GENERAL_DOT("[-+]?\\d+(\\.\\d+)?"),
    /**
     * Pattern: "[-+]?\d+(,\d+)?"
     */
    REGEX_NUMBERS_GENERAL_COMMA("[-+]?\\d+(,\\d+)?"),
    /**
     * Pattern: "([+\-]?\d+)"
     */
    REGEX_NUMBERS_INTEGER("([+\\-]?\\d+)");

    private final String pattern;

}
