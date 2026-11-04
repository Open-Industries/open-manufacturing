package org.openindustries.manufacturing.quality;

import java.util.UUID;

/**
 * QualityRule aggregate — a decision rule of this core: for one productFamily, it guarantees a
 * capacity (maxBatchSize) and a threshold (maxDefectPpm), with a precedence priority.
 * Only ACTIVE rules decide; every decision names its rule and its reason (audit).
 */
public class QualityRule {
    public UUID id;
    public String name;
    public String productFamily;
    public long maxBatchSize;
    public int maxDefectPpm;
    public int priority;
    public String status;

    public static QualityRule create(String name, String productFamily, long maxBatchSize, int maxDefectPpm, int priority) {
        QualityRule r = new QualityRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.productFamily = productFamily;
        r.maxBatchSize = maxBatchSize;
        r.maxDefectPpm = maxDefectPpm;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
