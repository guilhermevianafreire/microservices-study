package dev.guilhermevianafreire.ms.serviceproduct.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.TimeZone;

@Component
public class LocaleTimezoneUtil {

    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }

    public TimeZone getCurrentTimeZone() {
        return LocaleContextHolder.getTimeZone();
    }

}
