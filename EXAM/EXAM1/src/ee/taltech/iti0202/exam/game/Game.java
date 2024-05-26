package ee.taltech.iti0202.exam.game;

public class Game {
    private Team team1;
    private Team team2;

    /**
     * Constructor that creates a new game between two teams.
     * Throws IllegalArgumentException if either team has less than six players.
     * @param team1 The first team.
     * @param team2 The second team.
     */
    public Game(Team team1, Team team2) {
        if (team1.getPlayers().size() < 6 || team2.getPlayers().size() < 6) {
            throw new IllegalArgumentException();
        }
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * Determines the winner of the game by comparing the scores of the two teams.
     * Each team's score is the sum of the ratings of its top six players.
     * Adds one game to the games played amount for each player in the game.
     * @return The winning team.
     */
    public Team game() {
        for (Player player : team1.getPlayers()) {
            player.playGame();
        }

        for (Player player : team2.getPlayers()) {
            player.playGame();
        }

        if (team1.getTeamScore() > team2.getTeamScore()) {
            return team1;
        } else {
            return team2;
        }
    }

    /**
     * Finds the best player of the game, i.e., the player with the highest rating.
     * If ratings are equal, returns the player who has played more games.
     * @return The best player of the game.
     */
    public Player findTheBestPlayerOfTheGame() {
        Player bestPlayer1 = team1.getBestPlayer();
        Player bestPlayer2 = team2.getBestPlayer();

        if (bestPlayer1.getRating() > bestPlayer2.getRating()) {
            return bestPlayer1;
        } else if (bestPlayer2.getRating() > bestPlayer1.getRating()) {
            return bestPlayer2;
        } else if (bestPlayer1.getGamesPlayed() > bestPlayer2.getGamesPlayed()) {
            return bestPlayer1;
        } else  {
            return bestPlayer2;
        }
    }

    /**
     * Returns a string representation of the game, mentioning the two competing teams.
     * @return A string representation of the game.
     */
    @Override
    public String toString() {
        return String.format("In this game compete - team1: %s and team2: %s!", team1.getName(), team2.getName());
    }
}

