package com.sevenatseven.mainEntities;
import com.sevenatseven.exceptions.RecordNotFoundException;
import com.sevenatseven.sideentities.Crime;
import com.sevenatseven.sideentities.CrimeType;
import com.sevenatseven.sideentities.DangerLevel;
import com.sevenatseven.utils.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Criminal extends Person {
    private String currentLocation;
    private DangerLevel dangerLevel;
    private String psychologicalProfile;
    private Map<Crime, Integer> crimeStatistics;
    private ArrayList<Crime> crimes;
    private static int id = 6;

    public Criminal(String firstName, String lastName, String currentLocation,
                    String psychologicalProfile) {
        super(firstName, lastName, String.valueOf(id));
        id++;
        this.currentLocation = currentLocation;
        this.psychologicalProfile = psychologicalProfile;
        this.crimes = new ArrayList<>();
        crimeStatistics = new HashMap<>();
    }

    public Criminal(String data) throws IOException {
        super(data.split(":")[0], data.split(":")[1], data.split(":")[2]);
        this.currentLocation = data.split(":")[4];
        this.dangerLevel = DangerLevel.valueOf(data.split(":")[5]);
        this.psychologicalProfile = data.split(":")[6];
        String crimesData = data.split(":")[7];
        this.crimes = new ArrayList<>();
        Model model = new Model("crimes");
        for (String crimeId : crimesData.split(",")) {
            try {
                this.crimes.add(new Crime(model.getRecordAt(crimeId)));
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
        }
        crimeStatistics = new HashMap<>();
        this.computeCrimeStatistics();
        this.criminalProfiling();
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public String getPsychologicalProfile() {
        return psychologicalProfile;
    }

    public void setPsychologicalProfile(String profile) {
        this.psychologicalProfile = profile;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void computeCrimeStatistics() {
        for (Crime crime : crimes) {
            if (!crimeStatistics.containsKey(crime)) {
                crimeStatistics.put(crime, 1);
            } else {
                crimeStatistics.put(crime, crimeStatistics.get(crime) + 1);
            }
        }
    }

    public void addCrime(Crime crime) {
        if(crimes == null) {
            crimes = new ArrayList<>();
        }
        crimes.add(crime);
        if (!crimeStatistics.containsKey(crime)) {
            crimeStatistics.put(crime, 1);
        } else {
            crimeStatistics.put(crime, crimeStatistics.get(crime) + 1);
        }
        this.criminalProfiling();
    }

    public void criminalProfiling() {
        int severeCrimes = 0;
        int totalCrimes = crimes.size();

        for (Crime crime : crimes) {
            CrimeType type = crime.getCrimeType();
            if (type == CrimeType.MURDER || type == CrimeType.ASSAULT) {
                severeCrimes++;
            }
        }

        double severityRatio = (double) severeCrimes / totalCrimes;

        if (severityRatio >= 0.5 || severeCrimes >= 5 || "High Risk".equalsIgnoreCase(psychologicalProfile)) {
            this.dangerLevel = DangerLevel.HIGH;
        } else if (severityRatio >= 0.2 || totalCrimes >= 3 || "Moderate Risk".equalsIgnoreCase(psychologicalProfile)) {
            this.dangerLevel = DangerLevel.MEDIUM;
        } else {
            this.dangerLevel = DangerLevel.LOW;
        }
    }
}
