package br.dev.gvf.shared.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class NumberValidation {

    /**
     * Check if a string contains an integer or decimal number.<br>
     * The decimal number can represented with a dot or comma.<br>
     * Numbers can have plus or minus prefix.
     *
     * @param value string witn a number
     * @return true if teh string comtains a valid number, false otherwise
     */
    public boolean isNumberGeneral(String value) {
        if (StringUtils.isBlank(value))
            return false;
        return value.matches(NumberPattern.REGEX_NUMBERS_GENERAL.getPattern());
    }

    /**
     * Check if a string contains an integer or decimal number.<br>
     * The decimal number is represented with a dot
     * Numbers can have plus or minus prefix.
     *
     * @param value string witn a number
     * @return true if teh string comtains a valid number, false otherwise
     */
    public boolean isNumberGeneralDot(String value) {
        if (StringUtils.isBlank(value))
            return false;
        return value.matches(NumberPattern.REGEX_NUMBERS_GENERAL_DOT.getPattern());
    }

    /**
     * Check if a string contains an integer or decimal number.<br>
     * The decimal number is represented with a comma
     * Numbers can have plus or minus prefix.
     *
     * @param value string witn a number
     * @return true if teh string comtains a valid number, false otherwise
     */
    public boolean isNumberGeneralComma(String value) {
        if (StringUtils.isBlank(value))
            return false;
        return value.matches(NumberPattern.REGEX_NUMBERS_GENERAL_COMMA.getPattern());
    }

    /**
     * Check if a string contains an integer.<br>
     * Numbers can have plus or minus prefix.
     *
     * @param value string witn a number
     * @return true if teh string comtains a valid number, false otherwise
     */
    public boolean isNumberInteger(String value) {
        if (StringUtils.isBlank(value))
            return false;
        return value.matches(NumberPattern.REGEX_NUMBERS_INTEGER.getPattern());
    }

}
