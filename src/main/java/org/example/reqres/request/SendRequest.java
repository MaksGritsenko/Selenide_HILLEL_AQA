package org.example.reqres.request;

import io.restassured.response.Response;
import org.example.reqres.Specifications;

import static io.restassured.RestAssured.given;


public class SendRequest {

    public static Response sendGetRequest(String endpoint) {
        return given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(endpoint);
    }

    public static Response sendPostRequest(String endpoint, Object reqresrRequestBody) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(reqresrRequestBody)
                .when()
                .post(endpoint);
    }
}
