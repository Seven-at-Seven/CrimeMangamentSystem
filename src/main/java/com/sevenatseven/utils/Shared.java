package com.sevenatseven.utils;

import com.sevenatseven.mainEntities.PoliceOfficer;
import com.sevenatseven.mainEntities.PoliceStation;
public class Shared {
    private static PoliceOfficer currentOfficer;
    private static PoliceStation station;

    public static PoliceOfficer getCurrentOfficer() {
        return currentOfficer;
    }

    public static void setCurrentOfficer(PoliceOfficer currentOfficer) {
        Shared.currentOfficer = currentOfficer;
    }

    public static PoliceStation getStation() {
        return station;
    }

    public static void setStation(PoliceStation station) {
        Shared.station = station;
    }
}
