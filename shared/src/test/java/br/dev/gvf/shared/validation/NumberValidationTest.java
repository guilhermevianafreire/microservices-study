package br.dev.gvf.shared.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class NumberValidationTest {

    private NumberValidation numberValidation;

    @BeforeEach
    void setUp() {
        numberValidation = new NumberValidation();
    }

    @AfterEach
    void tearDown() {
        numberValidation = null;
    }

    @Test
    void isNumberGeneral() {
        assertThat(numberValidation.isNumberGeneral("1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("+1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("-1")).isTrue();

        assertThat(numberValidation.isNumberGeneral("1.0")).isTrue();
        assertThat(numberValidation.isNumberGeneral("+1.0")).isTrue();
        assertThat(numberValidation.isNumberGeneral("-1.0")).isTrue();

        assertThat(numberValidation.isNumberGeneral("0.1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("+0.1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("-0.1")).isTrue();

        assertThat(numberValidation.isNumberGeneral("1,0")).isTrue();
        assertThat(numberValidation.isNumberGeneral("+1,0")).isTrue();
        assertThat(numberValidation.isNumberGeneral("-1,0")).isTrue();

        assertThat(numberValidation.isNumberGeneral("0,1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("+0,1")).isTrue();
        assertThat(numberValidation.isNumberGeneral("-0,1")).isTrue();

        assertThat(numberValidation.isNumberGeneral("a")).isFalse();
        assertThat(numberValidation.isNumberGeneral("I")).isFalse();
        assertThat(numberValidation.isNumberGeneral("1234e")).isFalse();

        assertThat(numberValidation.isNumberGeneral(null)).isFalse();
    }

    @Test
    void isNumberGeneralDot() {
        assertThat(numberValidation.isNumberGeneralDot("1")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("+1")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("-1")).isTrue();

        assertThat(numberValidation.isNumberGeneralDot("1.0")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("+1.0")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("-1.0")).isTrue();

        assertThat(numberValidation.isNumberGeneralDot("0.1")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("+0.1")).isTrue();
        assertThat(numberValidation.isNumberGeneralDot("-0.1")).isTrue();

        assertThat(numberValidation.isNumberGeneralDot("1,0")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("+1,0")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("-1,0")).isFalse();

        assertThat(numberValidation.isNumberGeneralDot("0,1")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("+0,1")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("-0,1")).isFalse();

        assertThat(numberValidation.isNumberGeneralDot("a")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("I")).isFalse();
        assertThat(numberValidation.isNumberGeneralDot("1234e")).isFalse();

        assertThat(numberValidation.isNumberGeneralDot(null)).isFalse();
    }

    @Test
    void isNumberGeneralComma() {
        assertThat(numberValidation.isNumberGeneralComma("1")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("+1")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("-1")).isTrue();

        assertThat(numberValidation.isNumberGeneralComma("1.0")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("+1.0")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("-1.0")).isFalse();

        assertThat(numberValidation.isNumberGeneralComma("0.1")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("+0.1")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("-0.1")).isFalse();

        assertThat(numberValidation.isNumberGeneralComma("1,0")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("+1,0")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("-1,0")).isTrue();

        assertThat(numberValidation.isNumberGeneralComma("0,1")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("+0,1")).isTrue();
        assertThat(numberValidation.isNumberGeneralComma("-0,1")).isTrue();

        assertThat(numberValidation.isNumberGeneralComma("a")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("I")).isFalse();
        assertThat(numberValidation.isNumberGeneralComma("1234e")).isFalse();

        assertThat(numberValidation.isNumberGeneralComma(null)).isFalse();
    }

    @Test
    void isNumberInteger() {
        assertThat(numberValidation.isNumberInteger("1")).isTrue();
        assertThat(numberValidation.isNumberInteger("+1")).isTrue();
        assertThat(numberValidation.isNumberInteger("-1")).isTrue();

        assertThat(numberValidation.isNumberInteger("1.0")).isFalse();
        assertThat(numberValidation.isNumberInteger("+1.0")).isFalse();
        assertThat(numberValidation.isNumberInteger("-1.0")).isFalse();

        assertThat(numberValidation.isNumberInteger("0.1")).isFalse();
        assertThat(numberValidation.isNumberInteger("+0.1")).isFalse();
        assertThat(numberValidation.isNumberInteger("-0.1")).isFalse();

        assertThat(numberValidation.isNumberInteger("1,0")).isFalse();
        assertThat(numberValidation.isNumberInteger("+1,0")).isFalse();
        assertThat(numberValidation.isNumberInteger("-1,0")).isFalse();

        assertThat(numberValidation.isNumberInteger("0,1")).isFalse();
        assertThat(numberValidation.isNumberInteger("+0,1")).isFalse();
        assertThat(numberValidation.isNumberInteger("-0,1")).isFalse();

        assertThat(numberValidation.isNumberInteger("a")).isFalse();
        assertThat(numberValidation.isNumberInteger("I")).isFalse();
        assertThat(numberValidation.isNumberInteger("1234e")).isFalse();

        assertThat(numberValidation.isNumberInteger(null)).isFalse();
    }
}
