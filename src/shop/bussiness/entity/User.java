package shop.bussiness.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private  int userId;
    private  String userName;
    private String password;
    private  String confirmPassword;
    private String fullName;
    private  boolean permission;
    private Date date;
    private boolean userStatus;
    private String emailUser;
    private String phoneUser;

    public User() {
    }

    public User(int userId, String userName, String password, String confirmPassword, String fullName, boolean permission, Date date, boolean userStatus, String emailUser, String phoneUser) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        this.permission = permission;
        this.date = date;
        this.userStatus = userStatus;
        this.emailUser = emailUser;
        this.phoneUser = phoneUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }
}
