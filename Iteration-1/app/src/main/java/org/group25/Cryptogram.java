/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.io.Serializable;
import java.util.HashMap;

public class Cryptogram implements Serializable {
  private Integer cryptogramID;
  private String solution;

  private HashMap<Character, Character> mappings;
  private HashMap<Character, Character> guesses;

  public Cryptogram(Integer cryptogramID) {
    this.cryptogramID = cryptogramID;
    this.solution = "Hello World this is a cryptogram";
    this.mappings = new HashMap<>();
    this.guesses = new HashMap<>();
  }

  public Integer getCryptogramID() {
    return cryptogramID;
  }

  public void setCryptogramID(Integer cryptogramID) {
    this.cryptogramID = cryptogramID;
  }

  public String getSolution() {
    return solution;
  }
}
