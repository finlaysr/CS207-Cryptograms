/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Stream;

public class Cryptogram implements Serializable {
  private Integer cryptogramID;
  private String solution;

  private HashMap<Character, Character> mappings;
  private HashMap<Character, Character> guesses;

  public Cryptogram(Integer cryptogramID) {
    this.cryptogramID = cryptogramID;
    // random number:
    Random rand = new Random();
    try (Stream<String> f = Files.lines(Paths.get("src/resources/sentences.txt"));
        Stream<String> lines = Files.lines(Paths.get("src/resources/sentences.txt"))) {
      long line = rand.nextLong(f.count());
      this.solution = lines.skip(line).findFirst().get();
    } catch (Exception e) {
      System.out.println("Couldn't read text file");
    }

    this.mappings = new HashMap<>();
    this.guesses = new HashMap<>();
  }

  public Integer getCryptogramID() {
    return cryptogramID;
  }

  public String getSolution() {
    return solution;
  }
}
