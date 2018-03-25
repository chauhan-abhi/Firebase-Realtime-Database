package com.mmil.abhi.quizzard.model;

/**
 * Created by abhi on 11/3/18.
 */

public class User {

  private String UserName;
  private String email;
  private String password;

  public User(){

  }
  public User(String UserName, String email, String password) {
    this.UserName = UserName;
    this.email = email;
    this.password = password;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    this.UserName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
