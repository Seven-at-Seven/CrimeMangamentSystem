package com.sevenatseven.mainEntities;
import java.util.Map;
import DangerLevel;

public class Criminal extends Person {
    private String current_location;
    private DangerLevel danger_level;
    private String behavioralPattern;
    private String psychologicalProfile;
    private Map<String, Integer> crimeStatistics;
    /* The crimeStatistics map stores crime types as keys (e.g., "Theft")
    and the number of occurrences as values (e.g., 5).
    It helps track how often each crime was committed. */

    public Criminal(String firstName, String lastName, String id, String national_id, String current_location,
                    DangerLevel danger_level, String behavioralPattern, String psychologicalProfile,
                    Map<String, Integer> crimeStatistics) {
        super(firstName, lastName, id, national_id);
        this.current_location = current_location;
        this.danger_level = danger_level;
        this.behavioralPattern = behavioralPattern;
        this.psychologicalProfile = psychologicalProfile;
        this.crimeStatistics = crimeStatistics;
    }

    public DangerLevel getDangerLevel() {
        return danger_level;
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
        return current_location;
    }

    // Method to display crime statistics
    public void displayCrimes() {
        System.out.println("Crime Statistics:");
        for (Map.Entry<String, Integer> entry : crimeStatistics.entrySet()) {
            System.out.println("Crime: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
