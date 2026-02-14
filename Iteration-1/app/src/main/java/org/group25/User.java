package org.group25;

import java.io.Serializable;

public class User implements Serializable {
    private final String username;
    private final int gamesTotal;
    private final int gamesWon;
    private final int currGame;

    public User(String username) {
        this.username = username;
        this.gamesTotal = 0;
        this.gamesWon = 0;
        this.currGame = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getGamesTotal() {
        return gamesTotal;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getCurrGame() {
        return currGame;
    }
}
