package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alpesh on 9/26/2018.
 */

public class ChangePasswordRequest {
    @SerializedName("UserID")
    @Expose
    public Integer userID;

    @SerializedName("APIKey")
    @Expose
    public String aPIKey;
    @SerializedName("OldPass")
    @Expose
    public String oldPass;
    @SerializedName("NewPass")
    @Expose
    public String newPass;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getaPIKey() {
        return aPIKey;
    }

    public void setaPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
