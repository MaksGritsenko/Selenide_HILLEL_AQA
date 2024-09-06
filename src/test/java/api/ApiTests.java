package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.reqres.pojo.ListResourceResponse;
import org.example.reqres.pojo.ResourceResponse;
import org.example.reqres.pojo.UserResponse;
import org.example.reqres.pojo.body_request.ReqresLoginRequest;
import org.example.reqres.request.SendRequest;
import org.example.reqres.request.SendRequestGet;
import org.example.reqres.request.SendRequestPost;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

public class ApiTests {

    @Test(testName = "Check single user", priority = 1)
    public void testGetSingleUser() {
        Response response = new SendRequestGet().sendGetRequest("/api/users/2");
        response.then().statusCode(HttpStatus.SC_OK);

        UserResponse userResponse = response.as(UserResponse.class);

        assertThat(userResponse.getData().getId(), equalTo(2));
        assertThat(userResponse.getData().getEmail(), equalTo("janet.weaver@reqres.in"));
        assertThat(userResponse.getData().getFirst_name(), equalTo("Janet"));
        assertThat(userResponse.getData().getLast_name(), equalTo("Weaver"));
        assertThat(userResponse.getData().getAvatar(), not(emptyOrNullString()));
        assertThat(userResponse.getSupport().getUrl(), not(emptyOrNullString()));
        assertThat(userResponse.getSupport().getText(), not(emptyOrNullString()));
    }

    @Test(testName = "Check single user is not found", priority = 2)
    public void testGetSingleUserNotFound() {
        Response response = new SendRequestGet().sendGetRequest("/api/users/23");
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(testName = "Check single resource", priority = 3)
    public void testGetSingleResource() {
        Response response = new SendRequestGet().sendGetRequest("/api/unknown/2");
        response.then().statusCode(HttpStatus.SC_OK);

        ResourceResponse resourceResponse = response.as(ResourceResponse.class);

        assertThat(resourceResponse.getData().getId(), equalTo(2));
        assertThat(resourceResponse.getData().getName(), equalTo("fuchsia rose"));
        assertThat(resourceResponse.getData().getYear(), equalTo(2001));
        assertThat(resourceResponse.getData().getColor(), equalTo("#C74375"));
        assertThat(resourceResponse.getData().getPantone_value(), equalTo("17-2031"));
        assertThat(resourceResponse.getSupport().getUrl(), not(emptyOrNullString()));
        assertThat(resourceResponse.getSupport().getText(), not(emptyOrNullString()));
    }

    @Test(testName = "Check list resource", priority = 4)
    public void testGetListResources() {
        Response response = new SendRequestGet().sendGetRequest("/api/unknown");
        response.then().statusCode(HttpStatus.SC_OK);

        ListResourceResponse listResourceResponse = response.as(ListResourceResponse.class);

        assertThat(listResourceResponse.getData(), not(empty()));
        listResourceResponse.getData().forEach(resource -> {
            assertThat(resource.getId(), notNullValue());
            assertThat(resource.getName(), notNullValue());
            assertThat(resource.getYear(), notNullValue());
            assertThat(resource.getColor(), notNullValue());
            assertThat(resource.getPantone_value(), notNullValue());
        });
        assertThat(listResourceResponse.getSupport().getUrl(), not(emptyOrNullString()));
        assertThat(listResourceResponse.getSupport().getText(), not(emptyOrNullString()));
    }

    @Test(testName = "Check single resource not found", priority = 5)
    public void testGetSingleResourceNotFound() {
        Response response = new SendRequestGet().sendGetRequest("/api/unknown/23");
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(testName = "Check is login successful", priority = 6)
    public void testLoginSuccessful() {
        Response response = new SendRequestPost().sendRequestPost(new ReqresLoginRequest("eve.holt@reqres.in", "cityslicka"));
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", not(emptyOrNullString()));
    }

    @Test(testName = "Check is login unsuccessful", priority = 7)
    public void testLoginUnsuccessful() {
        Response response = new SendRequestPost().sendRequestPost(new ReqresLoginRequest("eve.holt@reqres.in"));
        response.then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }
}
