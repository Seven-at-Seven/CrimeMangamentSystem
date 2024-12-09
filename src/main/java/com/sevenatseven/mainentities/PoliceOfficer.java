package com.sevenatseven.mainEntities;

public class PoliceOfficer extends Person {
    private String phoneNumber;
    private String email;
    private String password;
    private int salary;
    private String rank;
    private Department department;

    PoliceOfficer (String firstName, String lastName, String id, String nationalId,
                   int salary, String phoneNumber, String rank, String email, String password) {
        super(firstName, lastName, id, nationalId);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.email = email;
        this.password = password;

    }

    public void setSalary (int salary) {
       if(this.password.equals("police.admin_123"))
           this.salary = salary;
    }
    public int getSalary () {
        return salary;
    }



    public void setRank (String rank) {
        if (this.password.equals("police.admin_123"))
            this.rank = rank;
    }
    public String getRank () {
        return rank;
    }

    public Department getDepartment () {
        return department;
    }
}
