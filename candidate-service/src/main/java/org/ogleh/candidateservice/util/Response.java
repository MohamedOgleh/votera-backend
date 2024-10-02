package org.ogleh.candidateservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Response {
    public static ResponseEntity<Map<String, Object>> failedRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status", 0,
                "message", message

        ));
    }

    public static ResponseEntity<Map<String, Object>> successResponse(Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", 1,
                "data", data
        ));
    }
}
