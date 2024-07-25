package ai.shreds.Infrastructure;

import ai.shreds.Domain.DomainServiceStepEntity;
import ai.shreds.Shared.SharedApplicationWorkflowResponseDTO;
import ai.shreds.Shared.SharedWorkflowStepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class InfrastructureMongoDBWorkflowRepositoryImpl implements InfrastructureRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureMongoDBWorkflowRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<DomainServiceStepEntity> findStepsByService(List<String> services) {
        logger.info("Fetching steps for services: {}", services);
        Query query = new Query();
        query.addCriteria(Criteria.where("services").in(services));
        return mongoTemplate.find(query, DomainServiceStepEntity.class, "ServiceStepAssociations");
    }

    @Override
    public void saveWorkflow(SharedApplicationWorkflowResponseDTO workflow) {
        try {
            logger.info("Saving workflow: {}", workflow);
            mongoTemplate.save(workflow, "Workflows");
        } catch (Exception e) {
            logger.error("Error saving workflow: ", e);
            throw e;
        }
    }
}