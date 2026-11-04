package org.openindustries.manufacturing.functions.downtime;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores equipment risk signals (vibration, temperature, error rate) with an auditable rationale.
 * Transparent, auditable rules: NORMAL (< 35), WARNING (35-69), CRITICAL (>= 70);
 * the rationale is returned with the score for audit (ISO/IEC 27002 A.8.15).
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    public record Event(String lineId, int vibrationIndex, int temperatureIndex, int errorIndex) {
    }

    public record Score(String lineId, int score, String decision, String rationale) {
    }

    @Funq
    public Score downtimeRiskScorer(Event event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.vibrationIndex() >= 70) {
            score += 40;
            why.append("vibrationIndex >= 70; ");
        }
        if (event.temperatureIndex() >= 60) {
            score += 30;
            why.append("temperatureIndex >= 60; ");
        }
        if (event.errorIndex() >= 50) {
            score += 30;
            why.append("errorIndex >= 50; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new Score(event.lineId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
