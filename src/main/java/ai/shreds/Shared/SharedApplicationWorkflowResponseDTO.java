package ai.shreds.Shared;
  
 import java.util.ArrayList; 
 import java.util.List; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 /** 
  * Data Transfer Object to encapsulate the response format for the workflow creation process. 
  * It contains a list of workflow steps, each represented by SharedWorkflowStepDTO. 
  */ 
 @Getter 
 @Setter
 @AllArgsConstructor 
 public class SharedApplicationWorkflowResponseDTO { 
     private List<SharedWorkflowStepDTO> workflow; 
  
     /** 
      * Constructor to initialize the workflow list to prevent null pointer exceptions. 
      */ 
     public SharedApplicationWorkflowResponseDTO() { 
         this.workflow = new ArrayList<>(); 
     } 
 }