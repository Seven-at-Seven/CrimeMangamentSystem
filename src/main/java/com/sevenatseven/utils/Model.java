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

    /**
     * Constructs a Model with the specified model name.
     * @param modelName the name of the model
     */
    public Model(String modelName) {
        // if modelName is single word, add "s" to the end
        if (modelName.split("\\s+").length != 1) {
            this.modelName = modelName + "s";
        } else {
            this.modelName = modelName;
        }
    }

    /**
     * Constructs a Model with the specified model name and custom data directory.
     * @param modelName the name of the model
     * @param customDataDir the custom directory for data storage
     */
    public Model(String modelName, String customDataDir) {
        if (modelName.split("\\s+").length != 1) {
            this.modelName = modelName + "s";
        } else {
            this.modelName = modelName;
        }
        this.customDataDir = customDataDir;
    }

    /**
     * Gets the file path for the model data.
     * @return the file path as a String
     */
    private String getFilePath() {
        if(customDataDir != null) {
            return customDataDir + modelName + ".txt";
        }
        return DATA_DIR + modelName + ".txt";
    }

    /**
     * Retrieves a record from the file by its ID.
     * @param recordId the ID of the record to retrieve
     * @return the record as a String, or null if not found
     * @throws IOException if an I/O error occurs
     */
    public String getRecordAt(String recordId) throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(":");
                if (words.length > 0 && words[0].equals(recordId)) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        return null;
    }
    /**
     * Retrieves a record from the file by its email.
     * @param email the email of the record to retrieve
     * @return the record as a String, or null if not found
     * @throws IOException if an I/O error occurs
     */
    public String getRecordByEmail(String email) throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(email)) {
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
     * Retrieves all records from the file.
     * @return a List of all records
     * @throws IOException if an I/O error occurs
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
     * Updates a record at the specified ID with new data.
     * @param recordId the ID of the record to update
     * @param data the new data to update the record with
     * @throws IOException if an I/O error occurs
     */
    public void updateRecordAt(String recordId, String data) throws IOException {
        String filePath = getFilePath();
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        
        // Read all lines and modify the target line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(":");
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

    /**
     * Saves a new record to the file.
     * @param data the data to save as a new record
     */
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

    /**
     * Deletes a record from the file by its ID.
     * @param recordId the ID of the record to delete
     */
    public void deleteRecordAt(String recordId) {
        String filePath = getFilePath();
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(":");
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