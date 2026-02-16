/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class AppData {
  private User currentUser;
  private ArrayList<User> users;
  private ArrayList<Cryptogram> cryptograms;

  public AppData() {

    users = new ArrayList<>();
    cryptograms = new ArrayList<>();
    loadUsers();
    System.out.println("Users:");
    users.forEach(user -> System.out.println(" - " + user.getUsername()));
  }

  public void addUser(String username) {
    users.add(new User(username));
    setCurrentUser(username);
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public Stream<User> getUsers() {
    return users.stream();
  }

  public boolean setCurrentUser(String username) {
    currentUser =
        users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    return currentUser != null;
  }

  public Stream<Cryptogram> getCryptograms() {
    return cryptograms.stream();
  }

  public Integer addCryptogram() {
    Integer nextCryptogramId =
        getCryptograms().mapToInt(Cryptogram::getCryptogramID).max().orElse(-1) + 1;
    cryptograms.add(new Cryptogram(nextCryptogramId));
    return cryptograms.getLast().getCryptogramID();
  }

  public Cryptogram getCryptogram(Integer cryptogramID) {
    return getCryptograms()
        .filter(c -> c.getCryptogramID().equals(cryptogramID))
        .findFirst()
        .orElse(null);
  }

  File usersDir = new File("src" + File.separator + "data" + File.separator + "users");

  void loadUsers() {
    File[] usersFiles = this.usersDir.listFiles();
    System.out.println(Arrays.toString(usersFiles));
    for (File file : usersFiles) {
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
