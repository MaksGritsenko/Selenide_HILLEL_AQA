package org.example.reqres.request;

import io.restassured.response.Response;
import org.example.reqres.pojo.body_request.ReqresLoginRequest;

public class SendRequestPost extends SendRequest {

    public Response sendRequestPost(ReqresLoginRequest request) {
        return sendGetRequest()
                .body(request)
                .post("/api/login");
    }
}