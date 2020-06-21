package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateScoreRequest {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("TournamentTeamID")
    @Expose
    private Integer tournamentTeamID;
    @SerializedName("Team1Score")
    @Expose
    private Integer team1Score;
    @SerializedName("Team2Score")
    @Expose
    private Integer team2Score;

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

    public Integer getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(Integer team1Score) {
        this.team1Score = team1Score;
    }

    public Integer getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(Integer team2Score) {
        this.team2Score = team2Score;
    }
}
