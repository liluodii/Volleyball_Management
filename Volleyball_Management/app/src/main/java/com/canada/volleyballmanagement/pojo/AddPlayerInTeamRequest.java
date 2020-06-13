package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPlayerInTeamRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("PlayerIDs")
    @Expose
    private String playerIDs;
    @SerializedName("TeamID")
    @Expose
    private Integer teamID;

    public String getAPIKey() {
        return aPIKey;
    }

    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getPlayerIDs() {
        return playerIDs;
    }

    public void setPlayerIDs(String playerIDs) {
        this.playerIDs = playerIDs;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }


}
