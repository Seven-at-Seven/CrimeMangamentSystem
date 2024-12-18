package com.sevenatseven.mainEntities;

import com.sevenatseven.exceptions.AlreadyExistException;
import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.utils.Model;
import com.sevenatseven.utils.Shared;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Department {
    private final int id;
    private final String name;
    private final LocalDate activationDate;
    private final ArrayList<PoliceOfficer> officers; // Array of officers
    private final ArrayList<Case> cases;       // Array of cases

    // Constructor: Initializes name, id, activationDate, and arrays
    public Department(int id,String name, LocalDate activationDate) {
        this.name = name;
        this.id = id;
        this.activationDate = activationDate;
        this.officers = new ArrayList<>();
        this.cases = new ArrayList<>();
    }
    public Department(String data) throws IOException {
        String[] data1 = data.split(":");
        id = Integer.parseInt(data1[0]);
        name = data1[1];
        activationDate = LocalDate.parse(data1[2]);
        officers = new ArrayList<>();
        cases = new ArrayList<>();
        Model officersModel = new Model("officers");
        for(String s : data1[3].split(",")){
            try {
                officers.add(new PoliceOfficer(officersModel.getRecordAt(s)));
            } catch (RecordNotFoundException e) {
                System.out.println(e);
            }
        }
        Model casesModel = new Model("cases");
        for(String s : data1[4].split(",")){
            try {
                cases.add(new Case(casesModel.getRecordAt(s)));
            } catch (RecordNotFoundException e) {
                System.out.println(e);
            }
        }
    }
    public void setCase(Case crimeCase) {
        for(Case c : cases){
            if(c.getCaseID() == crimeCase.getCaseID()){
                cases.remove(c);
                cases.add(crimeCase);
                return;
            }
        }
    }
    // 1. Function to add an officer
    public void addOfficer(PoliceOfficer officer) throws AlreadyExistException , NullPointerException {
        if(officer == null) throw new NullPointerException("officers are null");
        for(Department department : Shared.getStation().getDepartments()) {
            if (Shared.getStation().isOfficerIdUsed(officer.getId()))
                throw new AlreadyExistException("officer with ID: " + officer.getId() + " already exist");
        }
        officers.add(officer);
        Shared.getStation().addOfficerId(officer.getId());
    }

    // 2. Function to return all officers
    public ArrayList<PoliceOfficer> getOfficers() {
        return officers;
    }

    // 3. Function to add a case
    public void addCase(Case crimeCase) throws AlreadyExistException, NullPointerException {
        if(crimeCase == null) throw new NullPointerException("cases are null");
        if(Shared.getStation().isCaseIdUsed(crimeCase.getCaseID()))throw new AlreadyExistException("case with ID: " + crimeCase.getCaseID() + " already exist");
        cases.add(crimeCase);
        Shared.getStation().addCaseId(crimeCase.getCaseID());
    }

    // 4. Function to return all cases
    public ArrayList<Case> getCases() {
        return cases;
    }

    public PoliceOfficer getOfficer(String email) {
        for (PoliceOfficer officer : officers) {
            if (officer.getEmail().equals(email)) { // Assuming Officer has getId()
                return officer;
            }
        }
        return null; // Return null if officer is not found
    }
    public boolean officerExist(String Id) {
        for (PoliceOfficer officer : officers) {
            if (officer.getId().equals(Id)) {
                return true;
            }
        }
        return false;
    }
    // Getters for department attributes
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }
    public void removeOfficer(PoliceOfficer officer) {
        officers.remove(officer);
    }
    public void removeCase(Case crimeCase) {
        cases.remove(crimeCase);
    }
}
