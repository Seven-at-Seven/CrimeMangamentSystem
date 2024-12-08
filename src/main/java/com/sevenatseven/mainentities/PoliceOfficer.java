package com.sevenatseven.mainentities;

public class PoliceOfficer extends Person {
    private String phoneNumber;
    private int salary;
    private String rank;
    private String BadgeNumber;
    private Department department;

    PoliceOfficer (String firstName, String lastName,String nationalId, String phoneNumber, int salary, String rank,String BadgeNumber) {
        super(firstName, lastName, nationalId);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    this.BadgeNumber = BadgeNumber;
    }

    /**
     *
     * @param salary  The updated Salary
     * @param isAdmin Only salary can be updated by the admin
     */
    public void setSalary (int salary,boolean isAdmin) {
       if (isAdmin)this.salary = salary;
    }
    public int getSalary () {
        return salary;
    }


    /**
     *
     * @param rank     The updated rank
     * @param isAdmin  Admin only who can update the rank
     */
    public void setRank (String rank,boolean isAdmin) {
        if (isAdmin)this.rank = rank;
    }
    public String getRank () {
        return rank;
    }

    public Department getDepartment () {
        return department;
    }
}
