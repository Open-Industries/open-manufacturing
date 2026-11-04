package org.openindustries.manufacturing.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OpenMaterialStockRequest(
        @NotBlank String plantCode,
        @PositiveOrZero long availableUnits) {
}
