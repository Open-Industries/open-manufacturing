package org.openindustries.manufacturing.line;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ProductionLineResourceTest {

    @Test
    void createAndFetch() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"Line A1\",\"lineType\":\"ASSEMBLY\",\"plantCode\":\"PLANT-01\"}")
                .when().post("/api/v1/production-lines")
                .then().statusCode(201)
                .body("status", is("RUNNING"))
                .body("lineType", is("ASSEMBLY"))
                .extract().path("id");

        given().when().get("/api/v1/production-lines/" + id)
                .then().statusCode(200).body("status", is("RUNNING"));
    }

    @Test
    void transitionWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"name\":\"Line A1\",\"lineType\":\"ASSEMBLY\",\"plantCode\":\"PLANT-01\"}")
                .when().post("/api/v1/production-lines")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/production-lines/" + id + "/pause")
                .then().statusCode(200).body("status", is("PAUSED"));
    }
}
