package ee.taltech.iti0202.exam.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int nr;
    private int rating;
    private Team team;
    private int gamesPlayed;

    /**
     * Constructor that creates a new player with the specified name, number, and rating.
     * Throws IllegalArgumentException if the number is not between 1 and 100.
     * @param name The name of the player.
     * @param nr The number of the player.
     * @param rating The rating of the player.
     */
    public Player(String name, int nr, int rating) {
        this.name = name;
        if (nr > 1 && nr < 100) {
            this.nr = nr;
        } else {
            throw new IllegalArgumentException();
        }
        this.rating = rating;
        this.team = null;
        this.gamesPlayed = 0;
    }

    /**
     * Returns the team where the player belongs to.
     * @return The team where the player belongs to.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team for the player.
     * A player can belong to only one team and team can not be changed, if it exists already.
     * @param team The team to set for the player.
     */
    public void setTeam(Team team) {
        if (team != null) {
            this.team = team;
        }
    }

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of the player.
     */
    public int getNr() {
        return nr;
    }

    /**
     * Returns the rating of the player.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the number of games the player has played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Increments the number of games played by the player by one.
     */
    public void playGame() {
        gamesPlayed++;
    }

    /**
     * Allows changing the player's number if the new number is free and between 1 and 100.
     * Returns false if the number cannot be changed, otherwise true.
     * @param nr The new number to set.
     * @return True if the number was changed successfully, false otherwise.
     */
    public boolean changeNr(int nr) {
        List<Integer> numbers = new ArrayList<>();
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            numbers.add(player.getNr());
        }
        if (!numbers.contains(nr) && nr < 100 && nr > 1) {
            this.nr = nr;
            return true;
        }
        return false;
    }
}
