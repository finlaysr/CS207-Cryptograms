# CS207 Cryptograms Project -  User Stories - Group 25 2026

Callum Charteris, Duncun Amundrund, Finlay Robb, Luke Wallace, Muhammad Saad Khan

<br>

# User Stories 1 - Tracking progress

## Stakeholder : player

* As a player, I want to be able to view my stats like puzzles completed so I can then compare to other players in a leaderboard format.
    * As a player I want my name saved so that my personal progress can be saved and accessed as well as stored in the leaderboard.
    * As a player, I want my incomplete sessions to be saved, so that when I return to particular quiz the state remains as it was.
    * As a player, I want to be able to come back to playing after a while and still have my stats still stored in the leaderboard, so I don't lose my progress everytime I stop playing. 

### Acceptance Test:

#### Scenario: Only one player logged on

* **Given** that there is only one user logged on when the program is running
* **When** the user completes their game
* **Then** the user is shown only their scores

#### Scenario: Multiple players have logged in

* **Given** that multiple users have logged on and completed a game
* **When** the user completed their game
* **Then** the user is shown their score along with scores from other players

#### Scenario: No previous games have been completed

* **Given** that no previous games have been completed
* **When** the user finishes their game
* **Then** the user is only shown the score from their game

#### Scenario: Previous games have been completed

* **Given** that previous games have been completed
* **When** the user finishes their game
* **Then** the user is shown their score, along with the top 10 scores from previous games