package com.sevenatseven.mainentities;

abstract public class Person { // will use the setters for updating
    private String firstName;
    private String lastName;
    private String id;
    private String national_id;

    public Person(String firstName, String lastName, String id, String national_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.national_id = national_id;
    }

    public String get_name() {
        return firstName + " " + lastName;
    }

    public String get_id() {
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
        return national_id;
    }

    public void setNationalId(String national_id) {
        this.national_id = national_id;
    }
}

