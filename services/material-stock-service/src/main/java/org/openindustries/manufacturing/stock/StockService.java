package org.openindustries.manufacturing.stock;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StockService {

    @Inject
    StockRepository repository;

    public MaterialStock open(OpenMaterialStockRequest request) {
        return repository.saveAccount(MaterialStock.open(request.plantCode(), request.availableUnits()));
    }

    public MaterialStock get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "MaterialStock not found: " + id));
    }

    /**
     * Draws from the account. Invariant: the balance/capacity never goes negative —
     * an over-drawing operation is rejected (422) and nothing is recorded.
     */
    public MaterialIssue consume(UUID accountId, CreateMaterialIssueRequest request) {
        MaterialStock account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "MaterialStock is not ACTIVE");
        }
        if (request.units() > account.availableUnits) {
            throw new DomainException(422, "Insufficient capacity: requested " + request.units()
                    + " exceeds available " + account.availableUnits);
        }
        account.availableUnits -= request.units();
        repository.saveAccount(account);
        return repository.saveRecord(MaterialIssue.of(accountId, request.materialType(), request.units(), account.availableUnits));
    }

    public List<MaterialIssue> recordsOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
