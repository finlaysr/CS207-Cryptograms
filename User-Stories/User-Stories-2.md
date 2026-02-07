# User Stories 2 - Gameplay

## Stakeholder: Player

* As a player, I want to be able to take options to continue progress so I can complete the puzzle. 
    * As a player, I want to be able to receive a hint to solve one letter.
    * As a player, I want to be able to see the full solution for a current game if I feel I am fully stuck and can not make any more progress.
    * As a player, I want to be able to change my current puzzle if I find the difficulty too hard.
    * As a player, I would like tp be able to make unlimited mistakes with no consequence so as the quiz doesn't become fustrating and difficult.


* As a player, when I correctly decipher a letter I would like the game to autofill all other instances of this letter in the message so as it is more convinient and I don't have to re-type the same letter multiple times.


* As a player, I want to be able to have the option to play a large options of games, so as there is variety in the messages to be decoding and I am not repeating the same messages.

* As a player, I want to be able to view the rules so as when I'm playing for the first time I know what to do and I am not confused.

* As a player, I want to check the frequency of letters appearing in the message, as this will make it easier to solve the puzzle.

* As player, I want to be able to access a menu of all the options while playing the games, so I am aware of my command options (exit, solution, move back/forward, save).

* As a player, I want to able to see which letters haven't been used, so its more convienent which letters I still have to correctly decode.

* As a player, I want an error message when I use a letter that has already been used, so that I don't accidently put the same letter over and over again. 

* As a player, I want to receive stats at the end of a puzzle being completed so as I can view my progress and try to imporve for the next puzzle.

* As a player, I would like to be able to choose if my cryptogram is letter to letter or number to letter so as I can play on the mode i prefer and am most comfortable with.
## Acceptance Tests (2 per story)

### Scenario: Player is Stuck (1)

* **Given** that the player has started a game and is stuck
* **When** the player asks for a hint
* **Then** the program should insert a helpful letter so the player can continue their progress at no penalty to their stats.

#### Scenario: Player is Stuck (2)

* **Given** that the player has started a game and is stuck
* **When** the player asks for puzzle completition
* **Then** the solution of the puzzle is shown, the player can see the leaderboard, however this completion is not added to the stats for the player.

#### Scenario: Player is Stuck (3)
* **Given** that the player has started a game and is stuck
* **When** the player selects a new puzzle to be generated
* **Then** their current puzzle gets switched and progress restarted.


#### Scenario: Player Support
* **Given** the player is playing a puzzle
* **When** the player needs additional information
* **Then** they can see player help options like menu options, rules, frequency of letters and letters to still be solved.

#### Scenario: Player Guess
* **Given** the player is playing a puzzle
* **When** the player correctly guesses a letter
* **Then** the letter auto completes for all other exisiting instances and this letter cannot be guessed again. If guessed again an error message will be displayed.
