package dev.guilhermevianafreire.ms.serviceproduct.util;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleUtil {

  public Locale getCurrentLocale() {
    return LocaleContextHolder.getLocale(); 
  }
  
}
