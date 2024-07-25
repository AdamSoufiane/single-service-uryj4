package ai.shreds.shared; 
  
 import java.util.List; 
 import lombok.Data; 
  
 /** 
  * Data Transfer Object for workflow step details. 
  */ 
 @Data 
 public class SharedWorkflowStepDTO { 
     /** 
      * The sequence number of the step in the workflow. 
      */ 
     private Integer stepNumber; 
     /** 
      * The name of the workflow step. 
      */ 
     private String stepName; 
     /** 
      * The current state of the workflow step. 
      */ 
     private String stepState; 
     /** 
      * List of features associated with this step. 
      */ 
     private List<String> featureList; 
 }