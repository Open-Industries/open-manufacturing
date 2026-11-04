package org.openindustries.manufacturing.line;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateProductionLineRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "ASSEMBLY|MACHINING|PACKAGING|PAINTING") String lineType,
        @NotBlank String plantCode) {
}
