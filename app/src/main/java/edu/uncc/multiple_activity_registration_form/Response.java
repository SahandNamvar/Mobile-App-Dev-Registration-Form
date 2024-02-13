package edu.uncc.multiple_activity_registration_form;

import java.io.Serializable;

public class Response implements Serializable {
    private String name, email, role_id, educationLevel, maritalStatus, livingStatus, income;

    public Response(String name, String email, String role_id) {
        this.name = name;
        this.email = email;
        this.role_id = role_id;
    }

    public Response() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getLivingStatus() {
        return livingStatus;
    }

    public void setLivingStatus(String livingStatus) {
        this.livingStatus = livingStatus;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role_id='" + role_id + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", livingStatus='" + livingStatus + '\'' +
                ", income='" + income + '\'' +
                '}';
    }
}
