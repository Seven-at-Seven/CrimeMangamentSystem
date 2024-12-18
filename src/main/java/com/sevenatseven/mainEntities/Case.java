package com.sevenatseven.mainEntities;

import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.sideentities.CrimeType;
import com.sevenatseven.utils.Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Case {
    private final int caseID;
    private String description;
    private final LocalDate startDate;
    private LocalDate lastUpdateDate;
    private String crimeType;
    private int departmentID;
    private ArrayList<String> officersID;
    private ArrayList<Criminal> criminals;
    private static int nextCriminalID = 1;

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
        this.officersID.addAll(Arrays.asList(officersID));
        // criminals
        String[] criminalsID = words[5].split(",");
        this.criminals = new ArrayList<>();
        Model model = new Model("criminals");
        try {
            for (String criminalId : criminalsID) {
                this.criminals.add(new Criminal(model.getRecordAt(criminalId)));
                if (Integer.parseInt(criminalId) >= nextCriminalID) {
                    nextCriminalID = Integer.parseInt(criminalId) + 1;
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (RecordNotFoundException e) {
            System.out.println(e);
        }
        // for the adding of criminals
    }

    public Case(int caseIDFieldText, LocalDate startDatePickerValue, CrimeType crimeType, String description,int departmentID, String[] officersID) {
        this.caseID = caseIDFieldText;
        this.description = description;
        this.startDate = startDatePickerValue;
        this.lastUpdateDate = this.startDate;
        this.crimeType = crimeType.toString();
        this.departmentID = departmentID;
        this.officersID = new ArrayList<>();
        this.officersID.addAll(Arrays.asList(officersID));
        this.criminals = new ArrayList<>();
    }

    public static int getNextCriminalID() {
        return nextCriminalID;
    }

    public static void setNextCriminalID(int nextCriminalID) {
        Case.nextCriminalID = nextCriminalID;
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

    public ArrayList<String> getOfficersIds() {
        return officersID;
    }

    public ArrayList<Integer> getCriminalsIds() {
        ArrayList<Integer> criminalsIds = new ArrayList<>();
        for (Criminal criminal : criminals) {
            criminalsIds.add(Integer.parseInt(criminal.getId()));
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
    // for the adding of criminals
//    public void addCriminal(Criminal criminal) {
//        criminals.add(criminal);
//        criminalUsedIds.put(criminal.getId(), true);
//    }
//    public Boolean isCriminalUsed(String criminalId) {
//        return criminalUsedIds.containsKey(criminalId);
//    }
}

