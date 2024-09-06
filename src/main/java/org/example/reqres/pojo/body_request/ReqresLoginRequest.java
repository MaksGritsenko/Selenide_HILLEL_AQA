package org.example.reqres.pojo.body_request;

import org.example.hillel.testdata.User;
import org.example.reqres.pojo.UserCredential;

public class ReqresLoginRequest {

    private UserCredential userCredential;

    public ReqresLoginRequest(String userName, String password) {
        this.userCredential = new UserCredential(userName, password);
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }
}
