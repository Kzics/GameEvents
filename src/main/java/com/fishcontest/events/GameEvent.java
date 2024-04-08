package com.fishcontest.events;

public abstract class GameEvent {

    private final String name;
    private final String description;
    private final int duration;

    public GameEvent(final String name, final String description, final int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
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


    public abstract void start();
    public abstract void end();

}
