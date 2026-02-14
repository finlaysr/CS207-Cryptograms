package org.group25;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
    void saveUsers(ArrayList<User> users) {
        File dir = new File("src" + File.separator + "data" + File.separator + "users");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (User user : users) {
            try (FileOutputStream fos = new FileOutputStream("src/data/users/" + user.getUsername() + ".bin");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(user);
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        File[] usersDir = new File("src/data/users").listFiles();
        if (usersDir != null) {
            for (File file : usersDir) {
                if (file.getName().endsWith(".bin")) {
                    try (FileInputStream fis = new FileInputStream(file);
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        users.add((User) ois.readObject());

                    } catch (ClassNotFoundException | IOException error) {
                        System.out.println(error.getMessage());
                    }
                }
            }
        }

        return users;
    }
}
