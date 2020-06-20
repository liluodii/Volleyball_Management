package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEditTournamentTeamRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("TournamentTeamID")
    @Expose
    private Integer tournamentTeamID;
    @SerializedName("TournamentID")
    @Expose
    private Integer tournamentID;
    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("Team1")
    @Expose
    private Integer team1;
    @SerializedName("Team2")
    @Expose
    private Integer team2;

    public String getAPIKey() {
        return aPIKey;
    }

    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    public Integer getTournamentTeamID() {
        return tournamentTeamID;
    }

    public void setTournamentTeamID(Integer tournamentTeamID) {
        this.tournamentTeamID = tournamentTeamID;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getTeam1() {
        return team1;
    }

    public void setTeam1(Integer team1) {
        this.team1 = team1;
    }

    public Integer getTeam2() {
        return team2;
    }

    public void setTeam2(Integer team2) {
        this.team2 = team2;
    }

}
