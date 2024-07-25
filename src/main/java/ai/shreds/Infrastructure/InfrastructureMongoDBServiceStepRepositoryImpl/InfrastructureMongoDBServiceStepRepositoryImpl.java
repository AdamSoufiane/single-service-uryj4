package ai.shreds.infrastructure; 
  
 import ai.shreds.shared.DomainServiceStepEntity; 
 import ai.shreds.shared.SharedApplicationWorkflowResponseDTO; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.data.mongodb.core.MongoTemplate; 
 import org.springframework.data.mongodb.core.query.Criteria; 
 import org.springframework.data.mongodb.core.query.Query; 
 import org.springframework.stereotype.Repository; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.bson.Document; 
  
 import java.util.List; 
  
 @Repository 
 public class InfrastructureMongoDBServiceStepRepositoryImpl implements InfrastructureRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoDBServiceStepRepositoryImpl.class); 
  
     @Autowired 
     private MongoTemplate mongoTemplate; 
  
     @Override 
     public List<DomainServiceStepEntity> findStepsByService(List<String> services) { 
         try { 
             Query query = new Query(); 
             query.addCriteria(Criteria.where("services").in(services)); 
             logger.info("Querying steps by services."); 
             return mongoTemplate.find(query, DomainServiceStepEntity.class, "ServiceStepAssociations"); 
         } catch (DataAccessException e) { 
             logger.error("Database access exception occurred while querying steps by services: ", e); 
             throw e; 
         } catch (Exception e) { 
             logger.error("Error querying steps by services: ", e); 
             throw e; 
         } 
     } 
  
     @Override 
     public void saveWorkflow(SharedApplicationWorkflowResponseDTO workflow) { 
         try { 
             logger.info("Saving workflow."); 
             mongoTemplate.save(convertToDocument(workflow), "Workflows"); 
             logger.info("Workflow saved successfully."); 
         } catch (DataAccessException e) { 
             logger.error("Database access exception occurred while saving workflow: ", e); 
             throw e; 
         } catch (Exception e) { 
             logger.error("Error saving workflow: ", e); 
             throw e; 
         } 
     } 
  
     /** 
      * Converts the given SharedApplicationWorkflowResponseDTO to a MongoDB document format. 
      * 
      * @param workflow the workflow data transfer object to convert 
      * @return the MongoDB document representing the workflow 
      */ 
     private Document convertToDocument(SharedApplicationWorkflowResponseDTO workflow) { 
         Document doc = new Document(); 
         doc.append("requestId", workflow.getRequestId()); 
         doc.append("steps", workflow.getWorkflow().stream().map(step -> new Document("stepNumber", step.getStepNumber()).append("stepName", step.getStepName()).append("stepState", step.getStepState())).collect(Collectors.toList())); 
         return doc; 
     } 
 }