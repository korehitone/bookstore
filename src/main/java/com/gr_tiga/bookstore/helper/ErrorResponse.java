package com.gr_tiga.bookstore.helper;

import java.time.LocalDateTime;

public class ErrorResponse {
        private final String code;
        private final String message;
        private final String resource;
        private final String id;
        private final LocalDateTime timestamp;

        public ErrorResponse(String code, String message, String resource, String id) {
            this.code = code;
            this.message = message;
            this.resource = resource;
            this.id = id;
            this.timestamp = LocalDateTime.now();
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getResource() {
            return resource;
        }

        public String getId() {
            return id;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }