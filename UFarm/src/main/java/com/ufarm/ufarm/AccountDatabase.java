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

     
    @SuppressWarnings("unchecked")
    private void loadDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB_FILE))) {
            accounts = (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
             
            System.out.println("Creating new database file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     
    private void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     
    public boolean register(String name, String email, String password) {
        if (accounts.containsKey(email)) {
            return false;  
        }
        
        accounts.put(email, new User(name, email, password));
        saveDatabase();
        return true;
    }

     
    public boolean login(String email, String password) {
        User user = accounts.get(email);
        return user != null && user.password.equals(password);
    }

     
    public boolean emailExists(String email) {
        return accounts.containsKey(email);
    }
}
