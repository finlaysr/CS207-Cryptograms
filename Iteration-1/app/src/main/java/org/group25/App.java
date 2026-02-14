package org.group25;

import java.util.ArrayList;
import java.util.Iterator;

public class App {
    private static ArrayList<User> users;
    private static User currentUser;

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

    public static Iterator<User> getUsers() {
        return users.iterator();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

}
