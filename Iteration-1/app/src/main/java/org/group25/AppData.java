/* CS207 Cryptogram Project - Group 25 2026 */
package org.group25;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AppData {
  private User currentUser;
  private ArrayList<User> users;

  public AppData() {
    users = new ArrayList<>();
    loadUsers();
  }

  public void addUser(String username) {
    this.users.add(new User(username));
    setCurrentUser(username);
  }

  public Stream<User> getUsers() {
    return users.stream();
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public boolean setCurrentUser(String username) {
    currentUser =
        users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    return currentUser != null;
  }

  File usersDir = new File("src" + File.separator + "data" + File.separator + "users");

  void loadUsers() {
    File[] usersDir = this.usersDir.listFiles();
    if (usersDir != null) {
      for (File file : usersDir) {
        if (file.getName().endsWith(".ser")) {
          try (FileInputStream fis = new FileInputStream(file);
              ObjectInputStream ois = new ObjectInputStream(fis)) {
            users.add((User) ois.readObject());

          } catch (ClassNotFoundException | IOException error) {
            System.out.println(error.getMessage());
          }
        }
      }
    }
  }

  void saveUsers() {
    if (!usersDir.exists()) {
      usersDir.mkdirs();
    }

    for (User user : users) {
      try (FileOutputStream fos =
              new FileOutputStream(usersDir + File.separator + user.getUsername() + ".ser");
          ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(user);
      } catch (IOException error) {
        System.out.println(error.getMessage());
      }
    }
  }
}
