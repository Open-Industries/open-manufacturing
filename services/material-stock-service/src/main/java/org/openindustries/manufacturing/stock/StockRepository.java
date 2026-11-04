package org.openindustries.manufacturing.stock;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class StockRepository {
    private final ConcurrentHashMap<UUID, MaterialStock> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<MaterialIssue> records = new CopyOnWriteArrayList<>();

    public MaterialStock saveAccount(MaterialStock account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<MaterialStock> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public MaterialIssue saveRecord(MaterialIssue record) {
        records.add(record);
        return record;
    }

    public List<MaterialIssue> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
