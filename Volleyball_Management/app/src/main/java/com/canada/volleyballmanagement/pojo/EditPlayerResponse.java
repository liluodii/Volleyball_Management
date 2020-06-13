package com.canada.volleyballmanagement.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPlayerResponse {

    public class Data {

        @SerializedName("UserID")
        @Expose
        private Integer userID;
        @SerializedName("EmailID")
        @Expose
        private String emailID;
        @SerializedName("FirstName")
        @Expose
        private String firstName;
        @SerializedName("LastName")
        @Expose
        private String lastName;
        @SerializedName("ProfilePic")
        @Expose
        private String profilePic;
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("Contact")
        @Expose
        private String contact;
        @SerializedName("DOB")
        @Expose
        private String dOB;
        @SerializedName("JoinDate")
        @Expose
        private String joinDate;
        @SerializedName("Experience")
        @Expose
        private Double experience;
        @SerializedName("RoleID")
        @Expose
        private Integer roleID;
        @SerializedName("RoleName")
        @Expose
        private String roleName;
        @SerializedName("Address")
        @Expose
        private String address;

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public String getEmailID() {
            return emailID;
        }

        public void setEmailID(String emailID) {
            this.emailID = emailID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDOB() {
            return dOB;
        }

        public void setDOB(String dOB) {
            this.dOB = dOB;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public Double getExperience() {
            return experience;
        }

        public void setExperience(Double experience) {
            this.experience = experience;
        }

        public Integer getRoleID() {
            return roleID;
        }

        public void setRoleID(Integer roleID) {
            this.roleID = roleID;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
        private Data data;

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

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }


}
