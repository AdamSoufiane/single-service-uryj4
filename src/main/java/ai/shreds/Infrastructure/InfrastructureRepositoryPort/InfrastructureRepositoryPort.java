package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.DomainServiceStepEntity; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
 import java.util.List; 
  
 /** 
  * Interface for infrastructure repository operations. 
  */ 
 public interface InfrastructureRepositoryPort { 
     /** 
      * Finds the steps associated with the given list of services. 
      * @param services A list of service identifiers to search for in the database. 
      * @return A list of DomainServiceStepEntity matching the services. 
      */ 
     List<DomainServiceStepEntity> findStepsByService(List<String> services); 
  
     /** 
      * Saves the constructed workflow into the database. 
      * Handles exceptions related to database operations. 
      * @param workflow The workflow data to be saved. 
      * @throws DatabaseConnectionException, DataWriteException if there are issues accessing or writing to the database 
      */ 
     void saveWorkflow(SharedApplicationWorkflowResponseDTO workflow) throws DatabaseConnectionException, DataWriteException; 
 }