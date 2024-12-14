package com.sevenatseven.mainEntities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Department {
    private final String name;
    private final int id;
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

     //5. Function to return a case by ID
//           if (crimeCase.getCaseID() == caseId) { // Assuming Case has getId()
//        public Case getCaseById(int caseId) {
//        for (Case crimeCase : cases) {
//                return crimeCase;
//            }
//        }
//        return null; // Return null if case is not found
//    }

    // 6. Function to return an officer by ID
//    public Officer getOfficerById(int officerId) {
//        for (Officer officer : officers) {
//            if (officer.getId() == officerId) { // Assuming Officer has getId()
//                return officer;
//            }
//        }
//        return null; // Return null if officer is not found
//    }

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
