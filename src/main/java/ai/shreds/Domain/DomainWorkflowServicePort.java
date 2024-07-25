package ai.shreds.Domain;
  

 import ai.shreds.Shared.SharedApplicationWorkflowRequestDTO;
 import ai.shreds.Shared.SharedApplicationWorkflowResponseDTO;

 /**
  * DomainWorkflowServicePort defines the contract for creating workflows based on the provided service activation details. 
  * This interface acts as a primary port in the hexagonal architecture, allowing the domain services to interact with application services through adapters. 
  * It is crucial for the separation of core business logic from external concerns such as database interactions and API responses. 
  */

 public interface DomainWorkflowServicePort { 
     /** 
      * Creates a workflow based on the input request details. 
      * This method processes the input DTO to generate a workflow response DTO based on defined business rules. 
      * 
      * @param request the details of the service activation request 
      * @return a detailed workflow response based on the business rules and input data 
      */ 
     SharedApplicationWorkflowResponseDTO createWorkflow(SharedApplicationWorkflowRequestDTO request);
 }