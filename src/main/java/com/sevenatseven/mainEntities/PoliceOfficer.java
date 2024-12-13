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

    }
    public int getSalary () {
        return salary;
    }



    public void setRank (String rank) {

            this.rank = rank;
    }
    public String getRank () {
        return rank;
    }

    public Department getDepartment () {
        return department;
    }
}
