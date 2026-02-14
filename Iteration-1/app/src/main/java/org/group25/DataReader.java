package org.group25;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
    File dir = new File("src" + File.separator + "data" + File.separator + "users");

    void saveUsers(ArrayList<User> users) {
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (User user : users) {
            try (FileOutputStream fos = new FileOutputStream(dir + File.separator + user.getUsername() + ".bin");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(user);
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        File[] usersDir = dir.listFiles();
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
