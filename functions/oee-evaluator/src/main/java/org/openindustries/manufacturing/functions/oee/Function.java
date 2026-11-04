package org.openindustries.manufacturing.functions.oee;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares unplanned stops and OEE against line targets (ISO 22400 KPI discipline); one finding per breach.
 * Compares measured values with targets and returns a verdict with one finding per
 * breach — machine-checkable assurance evidence.
 */
public class Function {

    public record Kpi(String lineId, int measuredUnplannedStops, int targetUnplannedStops,
                      double measuredOeePct, double targetOeePct) {
    }

    public record Verdict(String lineId, boolean compliant, List<String> findings) {
    }

    @Funq
    public Verdict oeeEvaluator(Kpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredUnplannedStops() > kpi.targetUnplannedStops()) {
            findings.add("unplannedStops " + kpi.measuredUnplannedStops() + " exceeds target " + kpi.targetUnplannedStops());
        }
        if (kpi.measuredOeePct() < kpi.targetOeePct()) {
            findings.add("oeePct " + kpi.measuredOeePct() + " below target " + kpi.targetOeePct());
        }
        return new Verdict(kpi.lineId(), findings.isEmpty(), findings);
    }
}
