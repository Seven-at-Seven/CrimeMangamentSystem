package com.sevenatseven.mainEntities;

import com.sevenatseven.utils.Model;
import java.time.LocalDate;
import java.util.ArrayList;


public class Case {
    private final int caseID;
    private String description;
    private final LocalDate startDate;
    private LocalDate lastUpdateDate;
    private String crimeType;
    private int departmentID;
    private ArrayList<Integer> officersID;
    private ArrayList<Criminal> criminals;

    public Case(String caseData) {
        //id:description:crimeType:departmentID:officersID:criminals
        String[] words = caseData.split(":");
        this.caseID = Integer.parseInt(words[0]);
        this.description = words[1];
        this.startDate = LocalDate.now();
        this.lastUpdateDate = this.startDate;
        this.crimeType = words[2];
        this.departmentID = Integer.parseInt(words[3]);

        // officersID
        String[] officersID = words[4].split(",");
        this.officersID = new ArrayList<>();
        for (String officerID : officersID) {
            this.officersID.add(Integer.parseInt(officerID));
        }
        // criminals
        String[] criminalsID = words[5].split(",");
        this.criminals = new ArrayList<>();
        Model model = new Model("criminals");
        for (String criminalId : criminalsID) {
            this.criminals.add(new Criminal(model.getRecordAt(criminalId)));
        }
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

    public ArrayList<Integer> getOfficersIds() {
        return officersID;
    }

    public ArrayList<Integer> getCriminalsIds() {
        ArrayList<Integer> criminalsIds = new ArrayList<>();
        for (Criminal criminal : criminals) {
            criminalsIds.add(criminal.getCriminalID());
        }
        return criminalsIds;
    }
    public ArrayList<Criminal> getCriminals() {
        return criminals;
    }


    public void editCaseDetails(String newDescription, String newCrimeType, int newDepartmentID) {
        this.description = newDescription;
        this.crimeType = newCrimeType;
        this.departmentID = newDepartmentID;
        this.lastUpdateDate = LocalDate.now();
    }





}

