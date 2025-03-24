package com.rnd.gw.jason_validator;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JsonValidatorService {

    @Value("${schema.file.path}")
    private String schemaFilePath;

    public boolean validate(String jsonMessage) {
        try {
            String schemaString = new String(Files.readAllBytes(Paths.get(schemaFilePath)));
            JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaString));
            Schema schema = SchemaLoader.load(jsonSchema);

            JSONObject jsonSubject = new JSONObject(jsonMessage);
            schema.validate(jsonSubject); // Throws an exception if invalid

            return true;
        } catch (Exception e) {
            System.err.println("Invalid JSON message: " + e.getMessage());
            return false;
        }
    }
}

