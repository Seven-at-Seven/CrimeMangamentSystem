package com.sevenatseven.mainEntities;

public class Admin extends Person {
    private String name;
    private String email;
    private String password;



  public Admin(String data) {
    this(data.split(":"));
  }

  private Admin(String[] parts) {
    super(parts[0], parts[3], parts[4]); // id, firstName, lastName, nationalId
    this.email = parts[1]; // email
    this.password = parts[2]; // password

  }

  public Admin(String idFieldText, String firstNameFieldText, String lastNameFieldText, String emailFieldText, String passwordFieldText) {
    super(idFieldText, firstNameFieldText, lastNameFieldText);
    this.email = emailFieldText;
    this.password = passwordFieldText;
  }

  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }

}
