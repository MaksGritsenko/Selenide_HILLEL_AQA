package org.example.reqres.pojo.body_request;

import org.example.reqres.pojo.UserCredential;

public class ReqresLoginRequest {

    private String email;
    private String password;

    public ReqresLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ReqresLoginRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
