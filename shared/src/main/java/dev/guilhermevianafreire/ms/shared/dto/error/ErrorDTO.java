package dev.guilhermevianafreire.ms.shared.dto.error;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record ErrorDTO(String errorCode, String errorMessage, int statusCode, String statusDescription, String path, List<ErrorDetailDTO> errorDetails, Instant errorDateTime) {

    public ErrorDTO(Builder builder) {
        this(builder.errorCode, builder.errorMessage, builder.statusCode, builder.statusDescription, builder.path, builder.errorDetails, Instant.now());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        String errorCode;
        String errorMessage;
        int statusCode;
        String statusDescription;
        String path;
        List<ErrorDetailDTO> errorDetails;

        private Builder() { }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.statusCode = status.value();
            this.statusDescription = status.getReasonPhrase();
            return this;
        }

        public Builder statusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder errorDetails(List<ErrorDetailDTO> errorDetails) {
            this.errorDetails = errorDetails;
            return this;
        }

        public Builder errorDetail(ErrorDetailDTO errorDetail) {
            this.errorDetails = Collections.singletonList(errorDetail);
            return this;
        }

        public ErrorDTO build() throws IllegalStateException {
            return new ErrorDTO(this);
        }
    }

}
