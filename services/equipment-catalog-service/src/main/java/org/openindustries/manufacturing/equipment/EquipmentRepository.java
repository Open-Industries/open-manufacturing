package org.openindustries.manufacturing.equipment;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class EquipmentRepository {
    private final ConcurrentHashMap<UUID, Equipment> store = new ConcurrentHashMap<>();

    public Equipment save(Equipment entity) {
        store.put(entity.id, entity);
        return entity;
    }

    public Optional<Equipment> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Equipment> findAll() {
        return List.copyOf(store.values());
    }
}
