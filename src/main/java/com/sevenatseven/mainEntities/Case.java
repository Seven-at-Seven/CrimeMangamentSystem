package com.sevenatseven.mainEntities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Case {
    private final int caseID;
    private String description;
    private final LocalDate startDate;
    private LocalDate lastUpdateDate;
    private String crimeType;
    private int departmentID;
    private ArrayList<Integer> officersID;
    private ArrayList<Criminal> criminals;

    public Cases(String caseData) {
        this.caseID = caseID;
        this.description = description;
        this.startDate = LocalDate.now();
        this.lastUpdateDate = this.startDate;
        this.crimeType = crimeType;
        this.departmentID = departmentID;
        this.officersID = officersID;
        this.criminals = criminals;
    }
    public String getDescription() {
        return description;
    }

    public int getCaseID() {
        return caseID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public ArrayList<Integer> getOfficersID() {
        return officersID;
    }

    public ArrayList<Criminal> getCriminals() {
        return criminals;
    }






    public void editCaseDetails(String newDescription, String newCrimeType, int newDepartmentID,
                                ArrayList<Integer> newOfficersID, ArrayList<Criminal> newCriminals) {
        this.description = newDescription;
        this.crimeType = newCrimeType;
        this.departmentID = newDepartmentID;
        this.officersID = newOfficersID;
        this.criminals = newCriminals;
        this.lastUpdateDate = LocalDate.now();



    }





}

