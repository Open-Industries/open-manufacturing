package org.openindustries.manufacturing.functions.downtime;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyIsNormal() {
        given().contentType("application/json")
                .body("{\"lineId\":\"ln-1\",\"vibrationIndex\":7,\"temperatureIndex\":9,\"errorIndex\":3}")
                .when().post("/downtimeRiskScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"lineId\":\"ln-2\",\"vibrationIndex\":84,\"temperatureIndex\":78,\"errorIndex\":69}")
                .when().post("/downtimeRiskScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
