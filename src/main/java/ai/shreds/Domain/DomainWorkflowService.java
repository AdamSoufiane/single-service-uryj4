// File: src/main/java/ai/shreds/Domain/DomainWorkflowService.java
package ai.shreds.Domain;

import ai.shreds.Shared.SharedApplicationWorkflowRequestDTO;
import ai.shreds.Shared.SharedApplicationWorkflowResponseDTO;
import ai.shreds.Shared.SharedWorkflowStepDTO;
import ai.shreds.Infrastructure.InfrastructureRepositoryPort;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
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
            .filter(step -> {
                if (request.getType().equals("DEACTIVATION")) {
                    return request.getRightsProvisioning() || !step.isRights();
                } else {
                    return request.getRightsProvisioning() || (!step.isRights() && step.getServices().stream().anyMatch(request.getFeatureList()::contains));
                }
            })
            .map(step -> new SharedWorkflowStepDTO(null, step.getStep(), "INITIALIZED", step.getServices().stream().filter(request.getFeatureList()::contains).collect(Collectors.toList())))
            .collect(Collectors.toList());

        if (request.getType().equals("DEACTIVATION")) {
            boolean includeCVSDeallocation = false;

            if (request.getActivationWorkflow() != null) {
                includeCVSDeallocation = request.getActivationWorkflow().stream()
                    .anyMatch(step -> step.getStepName().equals("CVS_ALLOCATION") && step.getStepState().equals("SUCCESS"));
            }

            if (!includeCVSDeallocation && request.getRenewalWorkflow() != null) {
                includeCVSDeallocation = request.getRenewalWorkflow().stream()
                    .anyMatch(step -> step.getStepName().equals("CVS_UPDATE") && step.getStepState().equals("SUCCESS"));
            }

            if (includeCVSDeallocation) {
                workflowSteps.add(new SharedWorkflowStepDTO(null, "CVS_DEALLOCATION", "INITIALIZED", List.of("NAL03")));
            }
        }

        SharedApplicationWorkflowResponseDTO response = new SharedApplicationWorkflowResponseDTO();
        response.setWorkflow(workflowSteps);

        repositoryPort.saveWorkflow(response);
        logger.info("Workflow created successfully");

        return response;
    }
}