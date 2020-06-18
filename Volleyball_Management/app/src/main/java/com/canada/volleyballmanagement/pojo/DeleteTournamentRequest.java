package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteTournamentRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("TournamentID")
    @Expose
    private Integer tournamentID;

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

}
