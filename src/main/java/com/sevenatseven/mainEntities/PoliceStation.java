package com.sevenatseven.mainEntities;

import java.util.ArrayList;

public class PoliceStation {
    private String Name;
    private String Address;
    private ArrayList<Department> departments = new ArrayList<>();
    public PoliceStation() {
        Name = "";
        Address = "";
    }
    public PoliceStation(String name, String address) {
        Name = name;
        Address = address;
    }
    public PoliceStation(String name, String address, ArrayList<Department> departments) {
        Name = name;
        Address = address;
        this.departments = departments;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public ArrayList<Department> getDepartments() {
        return departments;
    }
    public void AddDepartment(Department department) {
        departments.add(department);
    }
    public void RemoveDepartment(Department department) {
        departments.remove(department);
    }
    public Department getDepartment(int ID) {
        for (Department department : departments) {
            if(department.getId() == ID) return department;
        }
        return null;
    }
    // get case
//    public Case getCase(int ID){
//        for (Department department : departments) {
//            for(Case cases : department.getCases()) {
//                if(cases.getID().equals(ID))return cases;
//            }
//        }
//        return null;
//    }
//    public PoliceOfficer getOfficer(String ID) {
//        for (Department department : departments) {
//            for (PoliceOfficer policeOfficer : department.getOfficers()) {
//                if(policeOfficer.getID().equals(ID))return policeOfficer;
//            }
//        }
//        return null;
//    }
}
