package dev.guilhermevianafreire.ms.serviceproduct.config;

import java.util.Locale;
import java.util.TimeZone;

import dev.guilhermevianafreire.ms.serviceproduct.util.LocaleTimezoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@RequiredArgsConstructor
public class ResourceBundleConfiguration implements WebMvcConfigurer {

  private final LocaleTimezoneUtil localeTimezoneUtil;

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    // TODO: Recover user defined locale and timezone info stored on the database.
    sessionLocaleResolver.setDefaultLocale(localeTimezoneUtil.getCurrentLocale());
    sessionLocaleResolver.setDefaultTimeZone(localeTimezoneUtil.getCurrentTimeZone());
    sessionLocaleResolver.setLocaleAttributeName("session.current.locale");
    sessionLocaleResolver.setTimeZoneAttributeName("session.current.timezone");
    return sessionLocaleResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("language");
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Bean("messageSource")
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("i18n/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

}
