package com.sevenatseven.mainEntities;


public class PoliceOfficer extends Person {
  private String phoneNumber;
  private String email;
  private String password;
  private int salary;
  private String rank;
  private Department department;

  public PoliceOfficer(String data) {
    this(data.split(":"));
  }

  private PoliceOfficer(String[] parts) {
    super(parts[3], parts[4], parts[0], parts[5]); // firstName, lastName, id, nationalId
    this.email = parts[1]; // email
    this.password = parts[2]; // password
    this.salary = Integer.parseInt(parts[6]); // salary
    this.phoneNumber = parts[7]; // phoneNumber
    this.rank = parts[8]; // rank
    this.department.id = parts[9];
    // exmaple: 1234:name@example.com:password:1234:1234:1234:1234:1234:1234:1234

  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
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
}
