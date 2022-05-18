package dev.guilhermevianafreire.ms.serviceproduct.bundle;

import dev.guilhermevianafreire.ms.serviceproduct.util.LocaleTimezoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageBundlesHelper {

    private final LocaleTimezoneUtil localeTimezoneUtil;
    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localeTimezoneUtil.getCurrentLocale());
    }

    public String getMessage(String code, Object... parameters) {
        return messageSource.getMessage(code, parameters, localeTimezoneUtil.getCurrentLocale());
    }

}
