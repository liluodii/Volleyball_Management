package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("Email")
    @Expose
    private String email;

    public String getAPIKey() {
        return aPIKey;
    }

    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
