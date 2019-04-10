package com.glassdoor.test.intern.first.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    public Map<Integer, String> userNames = new HashMap<>();
    public Map<Integer, String> addresses = new HashMap<>();

    public UserDatabase() {
        readDB();
    }

    public void readDB() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                UserDatabase.class.getClassLoader().getResourceAsStream("user_database.txt")))) {

            String line;
            while ((line = br.readLine()) != null) {
                String splits[] = line.split("\t");
                userNames.put(Integer.parseInt(splits[0]), splits[1]);
                addresses.put(Integer.parseInt(splits[0]), splits[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_new_user(Integer userid, String username, String address) {
        userNames.put(userid, username);
        addresses.put(userid, address);
    }
}