package com.sevenatseven.sideentities;

import java.time.LocalDate;

public class Crime {
    private String idInFile;
    private CrimeType crimeType;
    private LocalDate dateCommited;

    // Getter and Setter for idInFile
    public String getIdInFile() {
        return idInFile;
    }

    public void setIdInFile(String idInFile) {
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
        idInFile = data[0];
        dateCommited = LocalDate.parse(data[1]);
        crimeType = CrimeType.valueOf(data[2].toUpperCase());
    }
    public String toString(){
        return idInFile + ":" + dateCommited + ":" + crimeType;
    }
    public Crime(String idInFile, CrimeType crimeType, LocalDate dateCommited){
        this.idInFile = idInFile;
        this.crimeType = crimeType;
        this.dateCommited = dateCommited;
    }
}