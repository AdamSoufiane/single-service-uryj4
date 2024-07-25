package ai.shreds.adapter; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import ai.shreds.application.ApplicationCreateWorkflowInputPort; 
 import ai.shreds.shared.SharedApplicationWorkflowRequestDTO; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import ai.shreds.util.ResponseEntityBuilder; 
 import ai.shreds.util.CustomValidationException; 
 import ai.shreds.util.MissingFieldException; 
 import ai.shreds.util.InvalidFormatException; 
  
 @RestController 
 @RequestMapping("/api") 
 public class AdapterController { 
  
     private final ApplicationCreateWorkflowInputPort workflowService; 
     private static final Logger logger = LoggerFactory.getLogger(AdapterController.class); 
  
     @Autowired 
     public AdapterController(ApplicationCreateWorkflowInputPort workflowService) { 
         this.workflowService = workflowService; 
     } 
  
     @PostMapping("/activateServices") 
     public ResponseEntity<SharedApplicationWorkflowResponseDTO> createWorkflow(@RequestBody SharedApplicationWorkflowRequestDTO request) { 
         logger.info("Received activation request with ID: {} and type: {}", request.getRequestId(), request.getType()); 
         try { 
             validateRequestDTO(request); 
             SharedApplicationWorkflowResponseDTO response = workflowService.createWorkflow(request); 
             return ResponseEntityBuilder.ok(response); 
         } catch (MissingFieldException | InvalidFormatException e) { 
             logger.error("Validation error: {}", e.getMessage()); 
             return ResponseEntityBuilder.badRequest(e.getMessage()); 
         } catch (DatabaseAccessException e) { 
             logger.error("Database access error during operation on request ID: {}", request.getRequestId(), e); 
             return ResponseEntityBuilder.serverError(); 
         } 
     } 
  
     private void validateRequestDTO(SharedApplicationWorkflowRequestDTO request) throws MissingFieldException, InvalidFormatException { 
         if (request.getRequestId() == null || request.getRequestId().isEmpty()) throw new MissingFieldException("Request ID is missing."); 
         // Additional validation logic here 
     } 
 }