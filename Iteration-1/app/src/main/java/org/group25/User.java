/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.io.Serializable;

public class User implements Serializable {
  private final String username;
  private int gamesTotal;
  private int gamesWon;
  private Integer cryptogramID;

  public User(String username) {
    this.username = username;
    this.gamesTotal = 0;
    this.gamesWon = 0;
    this.cryptogramID = null;
  }

  public String getUsername() {
    return username;
  }

  public int getGamesTotal() {
    return gamesTotal;
  }

  public void incrementGamesTotal() {
    this.gamesTotal += 1;
  }

  public int getGamesWon() {
    return gamesWon;
  }

  public void incrementGamesWon() {
    this.gamesWon += 1;
  }

  public Integer getCryptogramID() {
    return cryptogramID;
  }

  public void setCryptogramID(Integer cryptogramID) {
    this.cryptogramID = cryptogramID;
  }
}
