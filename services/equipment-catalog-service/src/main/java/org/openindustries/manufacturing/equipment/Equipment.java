package org.openindustries.manufacturing.equipment;

import java.util.UUID;

/** Equipment aggregate — Equipment registry (Asset Administration Shell posture). */
public class Equipment {
    public UUID id;
        public String name;
        public String equipmentType;
        public int cycleTimeSec;
    public String status;

    public static Equipment create(String name, String equipmentType, int cycleTimeSec) {
        Equipment e = new Equipment();
        e.id = UUID.randomUUID();
        e.name = name;
        e.equipmentType = equipmentType;
        e.cycleTimeSec = cycleTimeSec;
        e.status = "AVAILABLE";
        return e;
    }
}
