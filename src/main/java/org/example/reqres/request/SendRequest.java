
package org.example.reqres.request;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class SendRequest {

    protected RequestSpecification sendGetRequest() {
        return given()
                .log().all()
                .baseUri("https://reqres.in")
                .contentType("application/json")
                .when();
    }
}

