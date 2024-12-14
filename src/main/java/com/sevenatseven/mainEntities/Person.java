package com.sevenatseven.mainEntities;

abstract public class Person { // will use the setters for updating
    private String firstName;
    private String lastName;
    private String id;
    private String nationalID;

    public Person(String id,String firstName, String lastName,  String nationalID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.nationalID = nationalID;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return nationalID;
    }

    public void setNationalId(String nationalId) {
        this.nationalID = nationalId;
    }
}

