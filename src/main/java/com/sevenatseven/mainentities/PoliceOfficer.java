package com.sevenatseven.mainentities;

public class PoliceOfficer extends Person {
    private String phoneNumber;
    private int salary;
    private String rank;
    private String BadgeNumber;

    PoliceOfficer (String firstName, String lastName,String nationalId, String phoneNumber, int salary, String rank,String BadgeNumber) {
        super(firstName, lastName, nationalId);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    this.BadgeNumber = BadgeNumber;
    }

    public void setSalary (int salary,boolean isAdmin) {
       if (isAdmin)this.salary = salary;
    }
    public int getSalary () {
        return salary;
    }



    public void setRank (String rank,boolean isAdmin) {
        if (isAdmin)this.rank = rank;
    }
    public String getRank () {
        return rank;
    }


}
