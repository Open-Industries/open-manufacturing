package org.openindustries.manufacturing.equipment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class EquipmentResourceTest {

    @Test
    void createAndTransition() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"CNC Mill 3\",\"equipmentType\":\"CNC\",\"cycleTimeSec\":45}")
                .when().post("/api/v1/equipment")
                .then().statusCode(201)
                .body("status", is("AVAILABLE"))
                .body("equipmentType", is("CNC"))
                .extract().path("id");

        given().when().post("/api/v1/equipment/" + id + "/deploy")
                .then().statusCode(200).body("status", is("DEPLOYED"));
    }
}
