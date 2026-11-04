package org.openindustries.manufacturing.equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateEquipmentRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "CNC|ROBOT|PRESS|CONVEYOR") String equipmentType,
        @Positive int cycleTimeSec) {
}
