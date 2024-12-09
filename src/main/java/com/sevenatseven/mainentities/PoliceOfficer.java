package com.sevenatseven.mainentities;

public class PoliceOfficer extends Person {
    private String phoneNumber;
    private int salary;
    private String rank;
    private Department department;

    PoliceOfficer (String firstName, String lastName,String nationalId,String id, String phoneNumber, int salary, String rank) {
        super(firstName, lastName,id, nationalId);
        this.salary = salary;
        this.phoneNumber = phoneNumber;

    }

    public void setSalary (int salary) {
       this.salary = salary;
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
