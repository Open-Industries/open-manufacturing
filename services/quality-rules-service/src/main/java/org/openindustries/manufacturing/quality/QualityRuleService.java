package org.openindustries.manufacturing.quality;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class QualityRuleService {

    @Inject
    QualityRuleRepository repository;

    public QualityRule create(CreateQualityRuleRequest request) {
        return repository.save(QualityRule.create(request.name(), request.productFamily(),
                request.maxBatchSize(), request.maxDefectPpm(), request.priority()));
    }

    public QualityRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "QualityRule not found: " + id));
    }

    public List<QualityRule> list() {
        return repository.findAll();
    }

    public QualityRule activate(UUID id) {
        QualityRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public Evaluation evaluate(EvaluateRequest request) {
        var candidates = repository.activeFor(request.productFamily());
        if (candidates.isEmpty()) {
            return new Evaluation("DENIED", null,
                    "No active rule for " + request.productFamily());
        }
        for (QualityRule rule : candidates) {
            if (rule.maxDefectPpm <= request.requiredMaxDefectPpm()
                    && rule.maxBatchSize >= request.requiredMaxBatchSize()) {
                return new Evaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the request");
            }
        }
        return new Evaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.productFamily() + " but none meets the requirement");
    }
}
