package org.ogleh.candidateservice.exceptions;

import org.ogleh.candidateservice.util.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class, MultipartException.class})
    public ResponseEntity<?> handleApiException(ApiException apiException) {
        return Response.failedRequest(apiException.getMessage());
    }
}
