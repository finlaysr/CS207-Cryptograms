/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

public class App {
  static AppData appData;

  public static void main(String[] args) {
    appData = new AppData();
    GUI gui = new GUI(appData);
  }

  public static void shutdown() {
    System.out.println("Users:");
    appData.getUsers().forEach(user -> System.out.println(user.getUsername()));
    appData.saveUsers();
  }

  public static AppData getAppData() {
    return appData;
  }
}
