package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTeamListResponse {

    public class Datum {

        @SerializedName("TeamID")
        @Expose
        private Integer teamID;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("TeamManagerID")
        @Expose
        private Integer teamManagerID;
        @SerializedName("TeamManagerName")
        @Expose
        private String teamManagerName;
        @SerializedName("Count")
        @Expose
        private Integer count;
        @SerializedName("Photo")
        @Expose
        private String photo;

        public Integer getTeamID() {
            return teamID;
        }

        public void setTeamID(Integer teamID) {
            this.teamID = teamID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTeamManagerID() {
            return teamManagerID;
        }

        public void setTeamManagerID(Integer teamManagerID) {
            this.teamManagerID = teamManagerID;
        }

        public String getTeamManagerName() {
            return teamManagerName;
        }

        public void setTeamManagerName(String teamManagerName) {
            this.teamManagerName = teamManagerName;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
