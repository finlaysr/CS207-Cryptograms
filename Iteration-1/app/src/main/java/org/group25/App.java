package org.group25;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class App {
    private static ArrayList<User> users;
    private static User currentUser;

    public static void main(String[] args) {
        startup();
        GUI gui = new GUI();
    }

    public static void startup() {
        DataReader dataReader = new DataReader();
        users = dataReader.loadUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    public static void addUser(String username) {
        users.add(new User(username));
    }

    public static void shutdown() {
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        DataReader dataReader = new DataReader();
        dataReader.saveUsers(users);
    }

    public static Stream<User> getUsers() {
        return users.stream();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }
}