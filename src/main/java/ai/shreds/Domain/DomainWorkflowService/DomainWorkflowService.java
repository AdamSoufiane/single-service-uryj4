package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedApplicationWorkflowRequestDTO; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
 import ai.shreds.shared.SharedWorkflowStepDTO; 
 import ai.shreds.infrastructure.InfrastructureRepositoryPort; 
 import java.util.List; 
 import java.util.stream.Collectors; 
 import org.springframework.transaction.annotation.Transactional; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class DomainWorkflowService implements DomainWorkflowServicePort { 
  
     private final InfrastructureRepositoryPort repositoryPort; 
     private static final Logger logger = LoggerFactory.getLogger(DomainWorkflowService.class); 
  
     public DomainWorkflowService(InfrastructureRepositoryPort repositoryPort) { 
         this.repositoryPort = repositoryPort; 
     } 
  
     @Override 
     @Transactional 
     public SharedApplicationWorkflowResponseDTO createWorkflow(SharedApplicationWorkflowRequestDTO request) { 
         logger.info("Starting workflow creation"); 
         if (request == null || request.getFeatureList() == null || request.getElementaryService() == null || !(request.getType().equals("ACTIVATION") || request.getType().equals("DEACTIVATION"))) { 
             logger.error("Invalid request data"); 
             throw new IllegalArgumentException("Invalid request data"); 
         } 
  
         List<DomainServiceStepEntity> steps = repositoryPort.findStepsByService(request.getFeatureList()); 
         if (steps == null) { 
             logger.error("No steps found for the services"); 
             throw new IllegalStateException("No steps found for the services"); 
         } 
  
         List<SharedWorkflowStepDTO> workflowSteps = steps.stream() 
             .filter(step -> step.getRights() == request.getRightsProvisioning()) 
             .map(step -> { 
                 if (step.getStep() == null) { 
                     logger.error("Step number is missing"); 
                     throw new IllegalArgumentException("Step number cannot be null"); 
                 } 
                 return new SharedWorkflowStepDTO(null, step.getStep(), "INITIALIZED", request.getFeatureList()); 
             }) 
             .collect(Collectors.toList()); 
  
         SharedApplicationWorkflowResponseDTO response = new SharedApplicationWorkflowResponseDTO(); 
         response.setWorkflow(workflowSteps); 
  
         repositoryPort.saveWorkflow(response); 
         logger.info("Workflow created successfully"); 
  
         return response; 
     } 
 }