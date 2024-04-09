package com.fishcontest.obj;

import org.bukkit.entity.Player;

public class EventPlayer {

    private final Player player;
    private int points;

    public EventPlayer(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(final int points) {
        this.points += points;
    }


}
