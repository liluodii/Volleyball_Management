package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TournamentTeamResponse {

    public class Datum {

        @SerializedName("TournamentTeamID")
        @Expose
        private Integer tournamentTeamID;
        @SerializedName("Team1")
        @Expose
        private Integer team1;
        @SerializedName("Team2")
        @Expose
        private Integer team2;
        @SerializedName("Team1Score")
        @Expose
        private Integer team1Score;
        @SerializedName("Team2Score")
        @Expose
        private Integer team2Score;
        @SerializedName("Team1Name")
        @Expose
        private String team1Name;
        @SerializedName("Team2Name")
        @Expose
        private String team2Name;
        @SerializedName("Team1Pic")
        @Expose
        private String team1Pic;
        @SerializedName("Team2Pic")
        @Expose
        private String team2Pic;
        @SerializedName("MatchDate")
        @Expose
        private String matchDate;

        public Integer getTournamentTeamID() {
            return tournamentTeamID;
        }

        public void setTournamentTeamID(Integer tournamentTeamID) {
            this.tournamentTeamID = tournamentTeamID;
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

        public String getTeam1Name() {
            return team1Name;
        }

        public void setTeam1Name(String team1Name) {
            this.team1Name = team1Name;
        }

        public String getTeam2Name() {
            return team2Name;
        }

        public void setTeam2Name(String team2Name) {
            this.team2Name = team2Name;
        }

        public String getTeam1Pic() {
            return team1Pic;
        }

        public void setTeam1Pic(String team1Pic) {
            this.team1Pic = team1Pic;
        }

        public String getTeam2Pic() {
            return team2Pic;
        }

        public void setTeam2Pic(String team2Pic) {
            this.team2Pic = team2Pic;
        }

        public String getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(String matchDate) {
            this.matchDate = matchDate;
        }

    }

        @SerializedName("ReturnCode")
        @Expose
        private String returnCode;
        @SerializedName("ReturnMsg")
        @Expose
        private String returnMsg;
        @SerializedName("ReturnValue")
        @Expose
        private String returnValue;
        @SerializedName("Data")
        @Expose
        private List<Datum> data = null;

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public String getReturnValue() {
            return returnValue;
        }

        public void setReturnValue(String returnValue) {
            this.returnValue = returnValue;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }
}
