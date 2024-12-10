package com.sevenatseven.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    private Model model;

    @BeforeEach
    void setUp() throws IOException {
        // Clear the admins file before each test
        new FileWriter("src/main/resources/data/admins.txt", false).close();
        model = new Model("admins");
    }
    
    @Test
    void testReadAllAdmins() throws IOException {
        // Add test records
        model.saveRecord( "1001 John Admin");
        model.saveRecord( "1002 Jane SuperAdmin");
        model.saveRecord( "1003 Bob SysAdmin");
        
        // Read and verify records
        List<String> records = new ArrayList<>();
        String filePath = "src/main/resources/data/admins.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
                System.out.println("Read record: " + line);
            }
        }
        
        assertEquals(3, records.size(), "Should have read 3 records");
        assertTrue(records.get(0).contains("1001"));
        assertTrue(records.get(1).contains("1002"));
        assertTrue(records.get(2).contains("1003"));
    }
    
    @Test
    void testGetSpecificRecord() throws IOException {
        // Add test records
        model.saveRecord("1001 John Admin");
        model.saveRecord("1002 Jane SuperAdmin");
        
        String record = model.getRecordAt("1002");
        assertNotNull(record, "Record should be found");
        assertTrue(record.contains("Jane"), "Record should contain the correct name");
    }
}
