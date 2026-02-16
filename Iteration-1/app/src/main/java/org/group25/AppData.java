/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AppData {
  private User currentUser;
  private ArrayList<User> users;
  private ArrayList<Cryptogram> cryptograms;

  public AppData() {
    users = new ArrayList<>();
    cryptograms = new ArrayList<>();
    loadData(new File("src" + File.separator + "data" + File.separator + "users"), users);
    loadData(
        new File("src" + File.separator + "data" + File.separator + "cryptograms"), cryptograms);

    System.out.println("Users:");
    users.forEach(user -> System.out.println(" - " + user.getUsername()));
    System.out.println("Cryptograms:");
    cryptograms.forEach(c -> System.out.println(" - " + c.getCryptogramID()));
  }

  public void saveAll() {
    saveData(new File("src" + File.separator + "data" + File.separator + "users"), users);
    saveData(
        new File("src" + File.separator + "data" + File.separator + "cryptograms"), cryptograms);
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

  public Integer newCryptogram() {
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

  public void removeCryptogram(Integer cryptogramID) {
    cryptograms.remove(getCryptogram(cryptogramID));
  }

  private <T extends Serializable> void loadData(File path, ArrayList<T> output) {
    if (!path.exists()) {
      path.mkdirs();
    } else {
      File[] files = path.listFiles();
      for (File file : files) {
        if (file.getName().endsWith(".ser")) {
          try (FileInputStream fis = new FileInputStream(file);
              ObjectInputStream ois = new ObjectInputStream(fis)) {
            output.add((T) ois.readObject());
          } catch (ClassNotFoundException | IOException error) {
            System.out.println(error.getMessage());
          }
        }
      }
    }
  }

  private <T extends Serializable> void saveData(File path, ArrayList<T> data) {
    if (!path.exists()) {
      path.mkdirs();
    }

    for (T thing : data) {
      String filename = "error";
      if (thing.getClass() == User.class) {
        filename = ((User) thing).getUsername();
      } else if (thing.getClass() == Cryptogram.class) {
        filename = ((Cryptogram) thing).getCryptogramID().toString();
      }

      try (FileOutputStream fos = new FileOutputStream(path + File.separator + filename + ".ser");
          ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(thing);
      } catch (IOException error) {
        System.out.println(error.getMessage());
      }
    }
  }
}
