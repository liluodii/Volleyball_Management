package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectTeamMemberResponse {

    public class Datum {

        @SerializedName("Photo")
        @Expose
        private String photo;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("TeamMemberJoinID")
        @Expose
        private Integer teamMemberJoinID;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTeamMemberJoinID() {
            return teamMemberJoinID;
        }

        public void setTeamMemberJoinID(Integer teamMemberJoinID) {
            this.teamMemberJoinID = teamMemberJoinID;
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
