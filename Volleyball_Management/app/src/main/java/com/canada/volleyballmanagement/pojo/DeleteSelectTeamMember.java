package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteSelectTeamMember {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("TeamMemberJoinID")
    @Expose
    private Integer teamMemberJoinID;

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

    public Integer getTeamMemberJoinID() {
        return teamMemberJoinID;
    }

    public void setTeamMemberJoinID(Integer teamMemberJoinID) {
        this.teamMemberJoinID = teamMemberJoinID;
    }

}
