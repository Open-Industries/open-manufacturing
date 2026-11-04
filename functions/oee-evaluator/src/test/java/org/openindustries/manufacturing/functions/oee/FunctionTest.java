package org.openindustries.manufacturing.functions.oee;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliant() {
        given().contentType("application/json")
                .body("{\"lineId\":\"ln-1\",\"measuredUnplannedStops\":1,\"targetUnplannedStops\":3,\"measuredOeePct\":87.0,\"targetOeePct\":85.0}")
                .when().post("/oeeEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void breachIsReported() {
        given().contentType("application/json")
                .body("{\"lineId\":\"ln-2\",\"measuredUnplannedStops\":9,\"targetUnplannedStops\":3,\"measuredOeePct\":87.0,\"targetOeePct\":85.0}")
                .when().post("/oeeEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
