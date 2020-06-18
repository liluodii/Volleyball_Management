package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEditTournamentRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("TournamentID")
    @Expose
    private Integer tournamentID;
    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getAPIKey() {
        return aPIKey;
    }

    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    public Integer getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(Integer tournamentID) {
        this.tournamentID = tournamentID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
