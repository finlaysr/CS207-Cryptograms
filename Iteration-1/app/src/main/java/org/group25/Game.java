/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

public class Game {
  Cryptogram currentCryptogram;
  AppData appData;

  public Game(AppData appData) {
    this.appData = appData;
  }

  public void newGame() {
    appData.removeCryptogram(appData.getCurrentUser().getCryptogramID());
    Integer cryptogramID = appData.newCryptogram();
    appData.getCurrentUser().setCryptogramID(cryptogramID);
    currentCryptogram = appData.getCryptogram(cryptogramID);
  }

  public boolean continueGame() {
    if (appData.getCurrentUser().getCryptogramID() == null) {
      return false;
    }
    currentCryptogram = appData.getCryptogram(appData.getCurrentUser().getCryptogramID());
    return true;
  }

  public Cryptogram getCurrentCryptogram() {
    return currentCryptogram;
  }
}
