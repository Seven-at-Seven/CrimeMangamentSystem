package com.sevenatseven.mainEntities;
import com.sevenatseven.utils.Shared;
import javafx.scene.control.Alert;

import java.util.ArrayList;


public class PoliceOfficer extends Person {
  private String phoneNumber;
  private String email;
  private String password;
  private int salary;
  private String rank;
  private int departmentId;

  public PoliceOfficer(String data) {
    this(data.split(":"));
  }

  private PoliceOfficer(String[] parts) {
    super(parts[0], parts[3], parts[4], parts[5]); // id, firstName, lastName, nationalId
    this.email = parts[1]; // email
    this.password = parts[2]; // password
    this.salary = Integer.parseInt(parts[6]); // salary
    this.phoneNumber = parts[7]; // phoneNumber
    this.rank = parts[8]; // rank
    this.departmentId = Integer.parseInt(parts[9]);
    // exmaple: 1234:name@example.com:password:1234:1234:1234:1234:1234:1234:1234

  }

  public Department getDepartment() {
    return Shared.getStation().getDepartment(departmentId);
  }



  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public ArrayList<Case> getCases() {
    try{
    ArrayList<Case> cases = new ArrayList<>();
    for (Department department : Shared.getStation().getDepartments()) {
      for (Case c : department.getCases()) {
        if (c.getOfficersIds().contains(this.getId())) {
          cases.add(c);
        }
      }
    }
    return cases;
    } catch (NullPointerException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Police Officer has no cases");
        alert.setHeaderText("Police Officer has no cases");
        alert.setContentText("Police Officer has no cases");
        alert.showAndWait();
      return new ArrayList<>();
    }
  }
}
