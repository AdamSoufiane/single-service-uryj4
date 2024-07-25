package ai.shreds.application; 
  
 import ai.shreds.shared.SharedApplicationWorkflowRequestDTO; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
 import ai.shreds.domain.DomainWorkflowServicePort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 public class ApplicationWorkflowService implements ApplicationCreateWorkflowInputPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationWorkflowService.class); 
     private final DomainWorkflowServicePort domainWorkflowService; 
  
     @Autowired 
     public ApplicationWorkflowService(DomainWorkflowServicePort domainWorkflowService) { 
         this.domainWorkflowService = domainWorkflowService; 
     } 
  
     @Override 
     public SharedApplicationWorkflowResponseDTO createWorkflow(SharedApplicationWorkflowRequestDTO request) { 
         logger.info("Starting workflow creation for request: {}", request.getRequestId()); 
         if (request == null || request.getFeatureList() == null || request.getFeatureList().isEmpty()) { 
             throw new IllegalArgumentException("Invalid request data"); 
         } 
         try { 
             // Validate input data 
             validateRequestData(request); 
             // Process the workflow creation 
             SharedApplicationWorkflowResponseDTO response = domainWorkflowService.createWorkflow(request); 
             logger.info("Workflow created successfully for request: {}", request.getRequestId()); 
             return response; 
         } catch (Exception e) { 
             logger.error("Error creating workflow for request: {}", request.getRequestId(), e); 
             throw e; 
         } 
     } 
  
     private void validateRequestData(SharedApplicationWorkflowRequestDTO request) { 
         // Validation logic here 
         if (request.getElementaryService() == null || request.getType() == null) { 
             throw new IllegalArgumentException("Missing required fields"); 
         } 
         // Additional validations can be added here 
     } 
 }