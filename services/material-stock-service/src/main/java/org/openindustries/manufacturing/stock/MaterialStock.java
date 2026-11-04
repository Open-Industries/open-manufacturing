package org.openindustries.manufacturing.stock;

import java.util.UUID;

/**
 * MaterialStock aggregate — the guarded resource account of this core: a balance/capacity that
 * consumption operations draw from. Core invariant (ADR-0004): it NEVER goes negative;
 * an operation that would overdraw it is rejected and records nothing.
 */
public class MaterialStock {
    public UUID id;
        public String plantCode;
    public long availableUnits;
    public String status;

    public static MaterialStock open(String plantCode, long availableUnits) {
        MaterialStock a = new MaterialStock();
        a.id = UUID.randomUUID();
        a.plantCode = plantCode;
        a.availableUnits = availableUnits;
        a.status = "ACTIVE";
        return a;
    }
}
