package ai.shreds.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ai.shreds.Shared.SharedApplicationWorkflowResponseDTO;

public class ResponseEntityBuilder {

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<SharedApplicationWorkflowResponseDTO> badRequest(SharedApplicationWorkflowResponseDTO body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static ResponseEntity<SharedApplicationWorkflowResponseDTO> serverError(SharedApplicationWorkflowResponseDTO body) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}