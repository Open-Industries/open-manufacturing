package org.openindustries.manufacturing.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateMaterialIssueRequest(
        @NotBlank @Pattern(regexp = "STEEL|PLASTIC|ELECTRONICS|CHEMICALS") String materialType,
        @Positive long units) {
}
