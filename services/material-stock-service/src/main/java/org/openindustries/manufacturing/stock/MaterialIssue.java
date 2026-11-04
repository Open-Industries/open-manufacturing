package org.openindustries.manufacturing.stock;

import java.time.Instant;
import java.util.UUID;

/** One guarded consumption record — the auditable evidence of a draw on a MaterialStock. */
public class MaterialIssue {
    public UUID id;
    public UUID accountId;
    public String materialType;
    public long units;
    public long remainingAfter;
    public Instant recordedAt;

    public static MaterialIssue of(UUID accountId, String materialType, long units, long remainingAfter) {
        MaterialIssue r = new MaterialIssue();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.materialType = materialType;
        r.units = units;
        r.remainingAfter = remainingAfter;
        r.recordedAt = Instant.now();
        return r;
    }
}
