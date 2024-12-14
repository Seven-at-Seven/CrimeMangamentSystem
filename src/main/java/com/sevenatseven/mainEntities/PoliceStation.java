package com.sevenatseven.mainEntities;

import com.sevenatseven.utils.Model;

import java.io.IOException;
import java.util.ArrayList;

public class PoliceStation {
    private String Name;
    private String Address;
    private final ArrayList<Department> departments;
    public PoliceStation() {
        Name = "";
        Address = "";
        departments = new ArrayList<>();
    }
    public PoliceStation(String name, String address, ArrayList<Department> departments) {
        Name = name;
        Address = address;
        this.departments = departments;
    }
    public PoliceStation(String name, String address) {
        Name = name;
        Address = address;
        departments = new ArrayList<>();
    }
    public PoliceStation(String Data) throws IOException {
        String[] data = Data.split(":");
        Name = data[0];
        Address = data[1];
        String[] data2 = data[2].split(",");
        departments = new ArrayList<>();
        Model model = new Model("departments");
        for (String s : data2) {

            departments.add(new Department(model.getRecordAt(s)));
        }
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
}
