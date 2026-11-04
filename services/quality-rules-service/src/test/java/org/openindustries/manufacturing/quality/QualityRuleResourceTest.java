package org.openindustries.manufacturing.quality;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class QualityRuleResourceTest {

    @Test
    void activateThenEvaluateAllowed() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"industrial-standard\",\"productFamily\":\"INDUSTRIAL\",\"maxBatchSize\":5000,\"maxDefectPpm\":100,\"priority\":1}")
                .when().post("/api/v1/quality-rules")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/quality-rules/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"productFamily\":\"INDUSTRIAL\",\"requiredMaxDefectPpm\":150,\"requiredMaxBatchSize\":3000}")
                .when().post("/api/v1/quality-rules/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"productFamily\":\"MEDICAL\",\"requiredMaxDefectPpm\":150,\"requiredMaxBatchSize\":3000}")
                .when().post("/api/v1/quality-rules/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
