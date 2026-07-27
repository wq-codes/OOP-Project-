package com.pocketpilot.manager;

import com.pocketpilot.model.User;

import java.io.*;
import java.util.ArrayList;

public class UserManager {

    int counter;
    private ArrayList<User> userlist;


    public UserManager() {
        userlist = new ArrayList<>();

        try {
            loaduser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     counter=userlist.size() + 1;
    }

    public void loaduser() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            User user = new User(parts[0], parts[1], parts[2], parts[3]);
            userlist.add(user);
        }
        reader.close();
    }

    public void saveuser() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"));

        for (User users : userlist) {
            String line = (users.getUserId() + "," + users.getUsername() + "," + users.getPassword() + "," + users.getEmail());
            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }

    public String gen_UserID() {

        String id = "U00" + counter;
        counter++;
        return id;
    }

    public void registeruser(String username, String password, String email) throws IOException {

        String generatedID = gen_UserID();

        User user = new User(generatedID, username, password, email);
        userlist.add(user);
        saveuser();

    }

    public User login(String username, String password) {

        for (User user : userlist) {

            if (username.equals(user.getUsername())) {

                if (password.equals(user.getPassword())) {

                    return user;
                }

            }

        }
        return null;
    }

}


