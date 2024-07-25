package ai.shreds.application; 
  
 import ai.shreds.shared.SharedApplicationWorkflowRequestDTO; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
  
 /** 
  * Interface for the input port that handles the creation of workflows in the application layer. 
  * This interface defines the contract for the workflow creation process. 
  * 
  * @apiNote The createWorkflow method processes the input DTO to generate a workflow based on business rules and data from the database. 
  * @param request The data transfer object containing details required to create the workflow. 
  * @return A response DTO representing the constructed workflow. 
  * @throws WorkflowCreationException if there are issues processing the input or accessing the database. 
  */ 
 public interface ApplicationCreateWorkflowInputPort { 
     /** 
      * Creates a workflow based on the given request. 
      * This method should handle any exceptions that might occur during the workflow creation process and ensure that all business rules are correctly applied. 
      * @param request The data transfer object containing details required to create the workflow. 
      * @return A response DTO representing the constructed workflow. 
      */ 
     SharedApplicationWorkflowResponseDTO createWorkflow(SharedApplicationWorkflowRequestDTO request) throws WorkflowCreationException; 
 }