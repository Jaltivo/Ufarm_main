/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ufarm.ufarm;

/**
 *
 * @author Admin
 * 
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountDatabase {

    private static final String DB_FILE = "account.dat";
    private Map<String, User> accounts;

    public AccountDatabase() {
        accounts = new HashMap<>();
        loadDatabase();
    }

    // User class to store account information
    private static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        String email;
        String password;

        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }

    // Load database from file
    @SuppressWarnings("unchecked")
    private void loadDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB_FILE))) {
            accounts = (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // First run - file doesn't exist yet
            System.out.println("Creating new database file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Save database to file
    private void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Register a new user
    public boolean register(String name, String email, String password) {
        if (accounts.containsKey(email)) {
            return false; // Email already exists
        }
        
        accounts.put(email, new User(name, email, password));
        saveDatabase();
        return true;
    }

    // Authenticate a user
    public boolean login(String email, String password) {
        User user = accounts.get(email);
        return user != null && user.password.equals(password);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return accounts.containsKey(email);
    }
}
