package ai.shreds.Application;


public class WorkflowCreationException extends RuntimeException {
    public WorkflowCreationException(String message) {
        super(message);
    }

    public WorkflowCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}