package org.openindustries.manufacturing.quality;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluateRequest(
        @NotBlank @Pattern(regexp = "CONSUMER|INDUSTRIAL|MEDICAL|AEROSPACE") String productFamily,
        @Positive int requiredMaxDefectPpm,
        @Positive long requiredMaxBatchSize) {
}
