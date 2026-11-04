package org.openindustries.manufacturing.stock;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class StockResourceTest {

    @Test
    void consumptionDrawsDown() {
        String id = given().contentType("application/json")
                .body("{\"plantCode\":\"PLANT-01\",\"availableUnits\":8000}")
                .when().post("/api/v1/material-stock")
                .then().statusCode(201).body("availableUnits", is(8000))
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"materialType\":\"STEEL\",\"units\":1200}")
                .when().post("/api/v1/material-stock/" + id + "/issues")
                .then().statusCode(200)
                .body("remainingAfter", is(6800));
    }

    @Test
    void overdrawIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"plantCode\":\"PLANT-01\",\"availableUnits\":8000}")
                .when().post("/api/v1/material-stock")
                .then().statusCode(201)
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"materialType\":\"STEEL\",\"units\":40000}")
                .when().post("/api/v1/material-stock/" + id + "/issues")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/material-stock/" + id)
                .then().statusCode(200).body("availableUnits", is(8000));
    }
}
