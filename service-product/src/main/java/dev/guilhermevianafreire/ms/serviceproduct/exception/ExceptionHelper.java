package dev.guilhermevianafreire.ms.serviceproduct.exception;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import dev.guilhermevianafreire.ms.serviceproduct.util.LocaleUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExceptionHelper {
  
  private final LocaleUtil localeUtil; 
  private final MessageSource errorMessagesSource;
  
  public InvalidEntitySaveOperationException buildInvalidEntitySaveOperationExceptionIdNotNull() {
    return new InvalidEntitySaveOperationException(errorMessagesSource.getMessage("service.save.id.present", null, localeUtil.getCurrentLocale()));
  }
  
  public void throwInvalidEntitySaveOperationExceptionIdNotNull() {
    throw buildInvalidEntitySaveOperationExceptionIdNotNull();
  }
  
  public InvalidEntityUbdateOperationException buildInvalidEntityUbdateOperationExceptionIdIsNull() {
    return new InvalidEntityUbdateOperationException(errorMessagesSource.getMessage("service.update.id.not.present", null, localeUtil.getCurrentLocale()));
  }
  
  public void throwInvalidEntityUbdateOperationExceptionIdIsNull() {
    throw buildInvalidEntityUbdateOperationExceptionIdIsNull();
  }
  
  public EntityNotFoundException buildEntityNotFoundException(UUID id) {
    return new EntityNotFoundException(errorMessagesSource.getMessage("service.update.entity.id.not.found", new Object[] {id.toString()}, localeUtil.getCurrentLocale()));
  }
  
  public void throwEntityNotFoundException(UUID id) {
    throw buildEntityNotFoundException(id);
  }
  
}
