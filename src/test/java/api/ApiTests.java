package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

public class ApiTests {

    static {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test (testName = "Check single user" , priority = 1)
    public void testGetSingleUser() {
        given()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", not(emptyOrNullString()))
                .body("support.url", not(emptyOrNullString()))
                .body("support.text", not(emptyOrNullString()));
    }

    @Test(testName = "Check single user is not found" , priority = 2)
    public void testGetSingleUserNotFound() {
        given()
                .when()
                .get("/api/users/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.anything());
    }

    @Test(testName = "Check single resource" , priority = 3)
    public void testGetSingleResource() {
        given()
                .when()
                .get("/api/unknown/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"))
                .body("support.url", not(emptyOrNullString()))
                .body("support.text", not(emptyOrNullString()));
    }

    @Test(testName = "Check list resource" , priority = 4)
    public void testGetListResources() {
        given()
                .when()
                .get("/api/unknown")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("total", notNullValue())
                .body("total_pages", notNullValue())
                .body("data", not(empty()))
                .body("data.id", everyItem(notNullValue()))
                .body("data.name", everyItem(notNullValue()))
                .body("data.year", everyItem(notNullValue()))
                .body("data.color", everyItem(notNullValue()))
                .body("data.pantone_value", everyItem(notNullValue()));
    }

    @Test(testName = "Check single resource not found" , priority = 5)
    public void testGetSingleResourceNotFound() {
        given()
                .when()
                .get("/api/unknown/23")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.anything());
    }

    @Test(testName = "Check is login successful" , priority = 6)
    public void testLoginSuccessful() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", not(emptyOrNullString()));
    }

    @Test(testName = "Check is login unsuccessful" , priority = 7)
    public void testLoginUnsuccessful() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\" }")
                .when()
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
