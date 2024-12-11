package com.sevenatseven.utils;

public class Shared {
    private static String officerString;


    public static String getOfficerString() {
        return officerString;
    }

    public static void setOfficerString(String officerString) {
        Shared.officerString = officerString;
    }
}

