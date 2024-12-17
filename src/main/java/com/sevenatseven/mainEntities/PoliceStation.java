package com.sevenatseven.mainEntities;

import com.sevenatseven.exceptions.AlreadyExistException;
import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.utils.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PoliceStation {
    private String Name;
    private String Address;
    private ArrayList<Department> departments;
    private Map<Integer, Boolean> departmentUsedIds;
    private Map<String, Boolean> adminsUsedIds;
    private ArrayList<Admin> admins;
    // private Person CurrentUser;
    public PoliceStation() {
        Name = "";
        Address = "";
        departments = new ArrayList<>();
        admins = new ArrayList<>();
        departmentUsedIds = new HashMap<>();
        adminsUsedIds = new HashMap<>();
    }
    public PoliceStation(String name, String address, ArrayList<Department> departments) {
        Name = name;
        Address = address;
        this.departments = departments;
        admins = new ArrayList<>();
        departmentUsedIds = new HashMap<>();
        for(Department department : departments) {
            departmentUsedIds.put(department.getId(), true);
        }
        adminsUsedIds = new HashMap<>();
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
        departmentUsedIds = new HashMap<>();
        for(Department department : departments) {
            departmentUsedIds.put(department.getId(), true);
        }
        adminsUsedIds = new HashMap<>();
        for(Admin admin : admins) {
            adminsUsedIds.put(admin.getId(), true);
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
    // Getters and Setters for PoliceStation attributes

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
    // functions for departments

    public ArrayList<Department> getDepartments() {
        return departments;
    }
    public void AddDepartment(Department department) throws AlreadyExistException {
        if(department == null) throw new NullPointerException("Department cannot be null");
        if(departmentUsedIds.containsKey(department.getId()))
            throw new AlreadyExistException("Department with ID " + department.getId() + " already exists");
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
    // functions for admin

    public Admin getAdminByEmail(String email) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email)) return admin;
        }
        return null;
    }
    public void AddAdmin(Admin admin) throws AlreadyExistException {
        if(admin == null) throw new NullPointerException("Admin cannot be null");
        if(adminsUsedIds.containsKey(admin.getId()))
            throw new AlreadyExistException("Admin with ID " + admin.getId() + " already exists");
        admins.add(admin);
    }
    public void RemoveAdmin(Admin admin) {
        admins.remove(admin);
    }
    public Boolean isDepartmentIdUsed(int id) {
        return departmentUsedIds.containsKey(id);
    }
    public Boolean isAdminIdUsed(String id) {
        return adminsUsedIds.containsKey(id);
    }
    // used for debugging

    public ArrayList<Admin> getAdmins() {
        return admins;
    }
}
