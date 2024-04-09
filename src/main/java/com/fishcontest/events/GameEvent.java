package com.fishcontest.events;

import com.fishcontest.Main;
import com.fishcontest.obj.EventLeaderboard;
import com.fishcontest.obj.EventPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEvent {

    private final String name;
    private final String description;
    private final int duration;
    protected boolean isPlaying;

    public GameEvent(final String name, final String description, final int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }


    public abstract void start(final Main main);
    public abstract void end(EventLeaderboard leaderboard);

}
