package ai.shreds.shared; 
  
 import java.util.List; 
 import lombok.Data; 
 import com.fasterxml.jackson.annotation.JsonProperty; 
  
 /** 
  * Data Transfer Object for handling workflow requests related to service activation. 
  * This class includes all necessary details to process and determine the appropriate workflow. 
  */ 
 @Data 
 public class SharedApplicationWorkflowRequestDTO { 
     @JsonProperty("requestId") 
     private String requestId; 
     @JsonProperty("featureList") 
     private List<String> featureList; 
     @JsonProperty("elementaryService") 
     private String elementaryService; 
     @JsonProperty("rightsProvisioning") 
     private Boolean rightsProvisioning; 
     @JsonProperty("activationWorkflow") 
     private String activationWorkflow; 
     @JsonProperty("renewalWorkflow") 
     private String renewalWorkflow; 
     @JsonProperty("type") 
     private String type; 
  
     /** 
      * Constructs a new request DTO with all necessary fields initialized. 
      * 
      * @param requestId The unique identifier for the request. 
      * @param featureList List of features or services requested. 
      * @param elementaryService The basic service involved in the request. 
      * @param rightsProvisioning Indicates if rights provisioning is needed. 
      * @param activationWorkflow The activation workflow details. 
      * @param renewalWorkflow The renewal workflow details. 
      * @param type The type of request (e.g., activation, deactivation). 
      * @throws IllegalArgumentException if any mandatory fields are null. 
      */ 
     public SharedApplicationWorkflowRequestDTO(String requestId, List<String> featureList, String elementaryService, Boolean rightsProvisioning, String activationWorkflow, String renewalWorkflow, String type) { 
         if (requestId == null || featureList == null || elementaryService == null) { 
             throw new IllegalArgumentException("Mandatory fields must not be null"); 
         } 
         this.requestId = requestId; 
         this.featureList = featureList; 
         this.elementaryService = elementaryService; 
         this.rightsProvisioning = rightsProvisioning; 
         this.activationWorkflow = activationWorkflow; 
         this.renewalWorkflow = renewalWorkflow; 
         this.type = type; 
     } 
 }