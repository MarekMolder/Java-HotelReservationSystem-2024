package ee.taltech.iti0202.exam.game;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Player> players;

    /**
     * Constructor that creates a new team with the specified name and list of players.
     * Throws IllegalArgumentException if there are more than 12 players in the list.
     * @param name The name of the team.
     * @param players The list of players in the team.
     */
    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    /**
     * Returns the name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of players in the team.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a player to the team. If the team already has 12 players, nothing happens.
     * @param player The player to add to the team.
     */
    public void addPlayer(Player player) {
        if (!players.contains(player) && players.size() < 12) {
            players.add(player);
        }
    }

    /**
     * Removes a player from the team if the player is in the team.
     * @param player The player to remove from the team.
     */
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            removePlayer(player);
        }
    }

    /**
     * Returns the score of the team by summing the ratings of the top six players.
     * @return The score of the team.
     */
    public int getTeamScore() {
        return players.stream().mapToInt(Player::getRating).sum();
    }

    /**
     * Returns the best player in the team, i.e., the player with the highest rating.
     * If ratings are equal, returns the player who has played more games.
     * @return The best player in the team.
     */
    public Player getBestPlayer() {
        return players.stream().max(Comparator.comparingInt(Player::getRating)
                .thenComparing(Player::getGamesPlayed).reversed())
                .orElse(null);
    }

    /**
     * Returns the worst player in the team, i.e., the player with the lowest rating.
     * If ratings are equal, returns the player who has played fewer games.
     * @return The worst player in the team.
     */
    public Player getWorstPlayer() {
        return players.stream().min(Comparator.comparingInt(Player::getRating)
                        .thenComparing(Player::getGamesPlayed).reversed())
                .orElse(null);
    }
}

