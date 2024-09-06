package org.example.reqres.request;

import io.restassured.response.Response;
public class SendRequestGet extends SendRequest {

    public Response sendGetRequest(String url) {
        return sendGetRequest().get(url);
    }
}
