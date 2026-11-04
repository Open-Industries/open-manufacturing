package org.openindustries.manufacturing.quality;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateQualityRuleRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "CONSUMER|INDUSTRIAL|MEDICAL|AEROSPACE") String productFamily,
        @Positive long maxBatchSize,
        @Positive int maxDefectPpm,
        @Min(1) @Max(100) int priority) {
}
