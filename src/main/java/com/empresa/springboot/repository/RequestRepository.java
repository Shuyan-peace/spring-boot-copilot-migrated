package com.empresa.springboot.repository;

import com.empresa.springboot.model.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

@Repository
public class RequestRepository implements IRequestRepository {

    private static final String DATA_FILE = "data/file.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private File getDataFile() throws IOException {
        // Always use a file relative to the working directory, not inside the JAR
        File file = new File(DATA_FILE);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            // Optionally, initialize with empty JSON object
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write("{}");
            }
        }
        return file;
    }

    @Override
    public Map<String, Request> findAll() {
        try {
            File file = getDataFile();
            if (!file.exists() || file.length() == 0) {
                return new HashMap<>();
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                Type type = new TypeToken<Map<String, Request>>(){}.getType();
                return gson.fromJson(br, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public List<Request> findAllAsList() {
        return new ArrayList<>(findAll().values());
    }

    @Override
    public List<Request> findByName(String name) {
        List<Request> result = new ArrayList<>();
        for (Request req : findAll().values()) {
            if (req.getName() != null && req.getName().equals(name)) {
                result.add(req);
            }
        }
        return result;
    }

    @Override
    public Request findHeadquarter(String name) {
        for (Request req : findAll().values()) {
            if ("Yes".equals(req.getHeadquarter()) && req.getName() != null && req.getName().equals(name)) {
                return req;
            }
        }
        return null;
    }

    @Override
    public Request findByKey(String key) {
        return findAll().get(key);
    }

    @Override
    public String save(Request request, String key) {
        Map<String, Request> map = findAll();
        map.put(key, request);
        try {
            File file = getDataFile();
            try (FileWriter writer = new FileWriter(file, false)) {
                gson.toJson(map, writer);
            }
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public String delete(String key) {
        Map<String, Request> map = findAll();
        if (map.remove(key) != null) {
            try {
                File file = getDataFile();
                try (FileWriter writer = new FileWriter(file, false)) {
                    gson.toJson(map, writer);
                }
                return "success";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error";
    }
}