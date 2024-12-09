package com.sevenatseven.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    protected String id;
    protected String modelName = null;
    private String customDataDir = null;
    private final String DATA_DIR = "src/main/resources/data/";

    public Model(String modelName) {
        this.modelName = modelName;
    }

    public Model(String modelName, String customDataDir) {
        this.modelName = modelName;
        this.customDataDir = customDataDir;
    }

    private String getFilePath() {
        if(customDataDir != null) {
            return customDataDir + modelName + ".txt";
        }
        return DATA_DIR + modelName + ".txt";
    }

    /**
     * Get a record from the file
     * @param recordId the id of the record to get
     * @return the record
     * @throws IOException if the file is not found
     */
    public  String getRecordAt(String recordId) throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length > 0 && words[0].equals(recordId)) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Get all records from the file
     * @return a list of all records
     * @throws IOException if the file is not found
     */
    public List<String> getAllRecords() throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return records;
    }

    /**
     * Update a record at a given recordId with the new data
     * @param recordId the id of the record to update
     * @param data the new data to update the record with
     * @throws IOException if the file is not found
     */
    public void updateRecordAt(String recordId, String data) throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        
        // Read all lines and modify the target line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length > 0 && words[0].equals(recordId)) {
                    line = data; // Update the line with new data
                }
                lines.add(line);
            }
        }
        
        // Write the updated lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void saveRecord(String data) {
        String filePath = getFilePath();
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecordAt(String recordId) {
        String filePath = getFilePath();
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length == 0 || !words[0].equals(recordId)) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Write the remaining lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}