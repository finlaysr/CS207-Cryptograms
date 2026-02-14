package org.group25;

import java.util.ArrayList;

public class App {
    private static ArrayList<User> users;

    public static void main(String[] args) {
        startup();
        GUI gui = new GUI();
    }

    public static void addUser(String username, String password) {
        users.add(new User(username));
    }

    public static void startup() {
        DataReader dataReader = new DataReader();
        users = dataReader.loadUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    public static void shutdown() {
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        DataReader dataReader = new DataReader();
        dataReader.saveUsers(users);
    }
}
