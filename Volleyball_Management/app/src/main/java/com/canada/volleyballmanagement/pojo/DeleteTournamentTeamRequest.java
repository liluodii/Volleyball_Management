package com.canada.volleyballmanagement.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteTournamentTeamRequest {

        @SerializedName("APIKey")
        @Expose
        private String aPIKey;
        @SerializedName("TournamentTeamID")
        @Expose
        private Integer tournamentTeamID;

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
}
