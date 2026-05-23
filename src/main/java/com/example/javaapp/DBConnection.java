package com.example.javaapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import java.util.Properties;

public class DBConnection {
    public static Connection connect() {
        try {
            // Reads .env from project root
            FileInputStream fis = new FileInputStream(".env");
            Properties props = new Properties();

            // Parse each line manually since .env isn't standard properties format
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    props.setProperty(parts[0].trim(), parts[1].trim());
                }
            }
            reader.close();

            return DriverManager.getConnection(
                    props.getProperty("DB_URL"),
                    props.getProperty("DB_USER"),
                    props.getProperty("DB_PASSWORD")
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}