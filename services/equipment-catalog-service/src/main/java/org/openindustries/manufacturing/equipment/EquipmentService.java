package org.openindustries.manufacturing.equipment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EquipmentService {

    @Inject
    EquipmentRepository repository;

    public Equipment create(CreateEquipmentRequest request) {
        return repository.save(Equipment.create(request.name(), request.equipmentType(), request.cycleTimeSec()));
    }

    public Equipment get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Equipment not found: " + id));
    }

    public List<Equipment> list() {
        return repository.findAll();
    }

    public Equipment deploy(UUID id) {
        Equipment entity = get(id);
        if (!"AVAILABLE".equals(entity.status)) {
            throw new DomainException(409, "Only a AVAILABLE equipment can transition via deploy");
        }
        entity.status = "DEPLOYED";
        return repository.save(entity);
    }
}
