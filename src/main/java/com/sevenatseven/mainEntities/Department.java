package com.sevenatseven.mainEntities;

import com.sevenatseven.utils.Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Department {
    private final int id;
    private final String name;
    private final LocalDate activationDate;
    private final ArrayList<PoliceOfficer> officers; // Array of officers
    private final ArrayList<Case> cases;       // Array of cases

    // Constructor: Initializes name, id, activationDate, and arrays
    public Department(String name, int id, LocalDate activationDate) {
        this.name = name;
        this.id = id;
        this.activationDate = activationDate;
        this.officers = new ArrayList<>();
        this.cases = new ArrayList<>();
    }
    public Department(String name, LocalDate activationDate){
        this.name = name;
        this.activationDate = activationDate;
        this.id = 0;
        this.officers = new ArrayList<>();
        this.cases = new ArrayList<>();
    }
    public Department(String data) throws IOException {
        String[] data1 = data.split(":");
        id = Integer.parseInt(data1[0]);
        name = data1[1];
        activationDate = LocalDate.parse(data1[2]);
        officers = new ArrayList<>();
        Model officersModel = new Model("officers");
        for(String s : data1[3].split(",")){
            //officers.add(new PoliceOfficer(officersModel.getRecordAt(s)));
        }
        cases = new ArrayList<>();
        Model casesModel = new Model("cases");
        for(String s : data1[4].split(",")){
            //cases.add(new Case(casesModel.getRecordAt(s)));
        }
    }
    public void setCase(Case crimeCase) {
        for(Case c : cases){
            if(c.getCaseID().equals(crimeCase.getCaseID())){
                cases.remove(c);
                cases.add(crimeCase);
                return;
            }
        }
    }
    // 1. Function to add an officer
    public void addOfficer(PoliceOfficer officer) {
        officers.add(officer);
    }

    // 2. Function to return all officers
    public ArrayList<PoliceOfficer> getOfficers() {
        return officers;
    }

    // 3. Function to add a case
    public void addCase(Case crimeCase) {
        cases.add(crimeCase);
    }

    // 4. Function to return all cases
    public ArrayList<Case> getCases() {
        return cases;
    }

    public PoliceOfficer getOfficerById(int officerId) {
        for (PoliceOfficer officer : officers) {
            if (officer.getId().equals(officerId)) { // Assuming Officer has getId()
                return officer;
            }
        }
        return null; // Return null if officer is not found
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
