package org.openindustries.manufacturing.quality;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class QualityRuleRepository {
    private final ConcurrentHashMap<UUID, QualityRule> store = new ConcurrentHashMap<>();

    public QualityRule save(QualityRule rule) {
        store.put(rule.id, rule);
        return rule;
    }

    public Optional<QualityRule> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<QualityRule> findAll() {
        return List.copyOf(store.values());
    }

    /** Active rules for a scope, highest priority (lowest number) first. */
    public List<QualityRule> activeFor(String productFamily) {
        return store.values().stream()
                .filter(r -> r.productFamily.equals(productFamily) && "ACTIVE".equals(r.status))
                .sorted(Comparator.comparingInt(r -> r.priority))
                .toList();
    }
}
