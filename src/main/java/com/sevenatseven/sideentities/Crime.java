package com.sevenatseven.sideentities;

import java.time.LocalDate;

public class Crime {
    private int idInFile;
    private CrimeType crimeType;
    private LocalDate dateCommited;
    private static int id = 10;

    // Getter and Setter for idInFile
    public int getIdInFile() {
        return idInFile;
    }

    public void setIdInFile(int idInFile) {
        this.idInFile = idInFile;
    }

    // Getter and Setter for crimeType
    public CrimeType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(CrimeType crimeType) {
        this.crimeType = crimeType;
    }

    // Getter and Setter for dateCommited
    public LocalDate getDateCommited() {
        return dateCommited;
    }

    public void setDateCommited(LocalDate dateCommited) {
        this.dateCommited = dateCommited;
    }
    public Crime(String data){
        this(data.split(":"));
    }
    private Crime(String[] data){
        idInFile = Integer.parseInt(data[0]);
        dateCommited = LocalDate.parse(data[1]);
        crimeType = CrimeType.valueOf(data[2].toUpperCase());
    }
    public String toString(){
        return idInFile + ":" + dateCommited + ":" + crimeType;
    }
    public Crime(CrimeType crimeType, LocalDate dateCommited){
        this.idInFile = id;
        id++;
        this.crimeType = crimeType;
        this.dateCommited = dateCommited;
    }
}