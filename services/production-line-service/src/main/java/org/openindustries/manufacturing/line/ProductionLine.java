package org.openindustries.manufacturing.line;

import java.time.Instant;
import java.util.UUID;

/**
 * ProductionLine aggregate — Production line registry per plant.
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): secret identifiers are validated at
 * creation and NEVER persisted or returned in clear — only a masked form is kept.
 * All identifiers in this reference implementation are synthetic.
 */
public class ProductionLine {
    public UUID id;
        public String name;
        public String lineType;
        public String plantCode;
    public String status;
    public Instant createdAt;

    public static ProductionLine create(String name, String lineType, String plantCode) {
        ProductionLine e = new ProductionLine();
        e.id = UUID.randomUUID();
        e.name = name;
        e.lineType = lineType;
        e.plantCode = plantCode;
        e.status = "RUNNING";
        e.createdAt = Instant.now();
        return e;
    }
}
