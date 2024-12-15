package com.sevenatseven.mainEntities;

import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.utils.Model;
import java.io.IOException;
import java.util.ArrayList;

public class PoliceStation {
    private String Name;
    private String Address;
    private ArrayList<Department> departments;
    private ArrayList<Admin> admins;
    public PoliceStation() {
        Name = "";
        Address = "";
        departments = new ArrayList<>();
        admins = new ArrayList<>();
    }
    public PoliceStation(String name, String address, ArrayList<Department> departments) {
        Name = name;
        Address = address;
        this.departments = departments;
        admins = new ArrayList<>();
    }
    public PoliceStation(String name, String address) {
        Name = name;
        Address = address;
        departments = new ArrayList<>();
        admins = new ArrayList<>();
    }
    public PoliceStation(String Data) throws IOException {
        String[] data = Data.split(":");
        Name = data[0];
        Address = data[1];
        String[] departmentsIds = data[2].split(",");
        departments = new ArrayList<>();
        Model model = new Model("departments");
        for (String s : departmentsIds) {
            try {
                departments.add(new Department(model.getRecordAt(s)));
            } catch (RecordNotFoundException e) {
                System.out.println(e);
            }
        }
        admins = new ArrayList<>();
        Model adminsModel = new Model("admins");
        for (String s : adminsModel.getAllRecords()) {
                admins.add(new Admin(s));
        }
    }
    public PoliceOfficer getOfficerByEmail(String email) throws RecordNotFoundException {
        for (Department department : departments) {
            for (PoliceOfficer officer : department.getOfficers()) {
                if (officer.getEmail().equals(email)) return officer;
            }
        }
        throw new RecordNotFoundException("Officer with email " + email + " not found");
    }
    public Admin getAdminByEmail(String email) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email)) return admin;
        }
        return null;
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
