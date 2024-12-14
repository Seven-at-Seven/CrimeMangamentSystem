package com.sevenatseven.mainEntities;
import com.sevenatseven.sideentities.DangerLevel;
import java.util.Map;



public class Criminal extends Person {
    private String currentLocation;
    private DangerLevel dangerLevel;
    private String behavioralPattern;
    private String psychologicalProfile;
    private Map<String, Integer> crimeStatistics;

    public Criminal(String firstName, String lastName, String id, String nationalId, String currentLocation,
                  String behavioralPattern, String psychologicalProfile,
                    Map<String, Integer> crimeStatistics) {
        super(firstName, lastName, id, nationalId);
        this.currentLocation = currentLocation;
        // this.dangerLevel = dangerLevel; compute danger level
        this.behavioralPattern = behavioralPattern;
        this.psychologicalProfile = psychologicalProfile;
        this.crimeStatistics = crimeStatistics;
    }
    // id:firstName:lastName:nationalId:currentLocation:dangerLevel:behavioralPattern:psychologicalProfile:crimeStatistics
    public Criminal(String data) {
        super(data.split(":")[0], data.split(":")[1], data.split(":")[2], data.split(":")[3]);
        this.currentLocation = data.split(":")[4];
        this.dangerLevel = DangerLevel.valueOf(data.split(":")[5]);
        this.behavioralPattern = data.split(":")[6];
        this.psychologicalProfile = data.split(":")[7];
        this.crimeStatistics = Map.of();
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public String getBehaviorPattern() {
        return behavioralPattern;
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

    // Method to display crime statistics
    public void displayCrimes() {
        System.out.println("Crime Statistics:");
        for (Map.Entry<String, Integer> entry : crimeStatistics.entrySet()) {
            System.out.println("Crime: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
