package org.openindustries.manufacturing.line;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductionLineService {

    @Inject
    ProductionLineRepository repository;

    public ProductionLine create(CreateProductionLineRequest request) {
        return repository.save(ProductionLine.create(request.name(), request.lineType(), request.plantCode()));
    }

    public ProductionLine get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "ProductionLine not found: " + id));
    }

    public List<ProductionLine> list() {
        return repository.findAll();
    }

    public ProductionLine pause(UUID id) {
        ProductionLine entity = get(id);
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "A retired productionline is terminal");
        }
        if ("PAUSED".equals(entity.status)) {
            throw new DomainException(409, "ProductionLine already PAUSED");
        }
        entity.status = "PAUSED";
        return repository.save(entity);
    }

    public ProductionLine retire(UUID id) {
        ProductionLine entity = get(id);
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "A retired productionline is terminal");
        }
        if ("RETIRED".equals(entity.status)) {
            throw new DomainException(409, "ProductionLine already RETIRED");
        }
        entity.status = "RETIRED";
        return repository.save(entity);
    }
}
