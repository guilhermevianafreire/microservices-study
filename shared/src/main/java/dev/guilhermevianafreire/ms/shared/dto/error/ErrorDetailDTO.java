package dev.guilhermevianafreire.ms.shared.dto.error;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public record ErrorDetailDTO(String cause, String stackTrace) {

    public ErrorDetailDTO(Builder builder) {
        this(builder.cause, builder.stackTrace);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        String cause;
        String stackTrace;

        private Builder() {
        }

        public Builder cause(String cause) {
            this.cause = cause;
            return this;
        }

        public Builder stackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public Builder stackTraceAbbreviated(Throwable stackTrace) {
            this.stackTrace = StringUtils.abbreviate(ExceptionUtils.getStackTrace(ExceptionUtils.getRootCause(stackTrace)), 100);
            return this;
        }

        public Builder stackTraceAbbreviated(Throwable stackTrace, int maxLength) {
            this.stackTrace = StringUtils.abbreviate(ExceptionUtils.getStackTrace(ExceptionUtils.getRootCause(stackTrace)), maxLength);
            return this;
        }

        public ErrorDetailDTO build() throws IllegalStateException {
            return new ErrorDetailDTO(this);
        }
    }

}
