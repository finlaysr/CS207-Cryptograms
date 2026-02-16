/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

public class Game {
  Cryptogram currentCryptogram;

  public Game(AppData appData) {
    if (appData.getCurrentUser().getCryptogramID() == null) {
      // If current user doesn't have a cryptogram, create a new one and assign it to the user
      Integer cryptogramID = appData.addCryptogram();
      System.out.println("New cryptogram ID: " + cryptogramID);
      appData.getCurrentUser().setCryptogramID(cryptogramID);
      System.out.println("here");
    }
    System.out.println("cryptogramID: " + appData.getCurrentUser().getCryptogramID());
    currentCryptogram = appData.getCryptogram(appData.getCurrentUser().getCryptogramID());

    System.out.println("Current Cryptogram: " + currentCryptogram.getSolution());
  }

  public Cryptogram getCurrentCryptogram() {
    return currentCryptogram;
  }
}
