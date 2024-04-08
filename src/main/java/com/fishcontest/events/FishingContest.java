package com.fishcontest.events;

public class FishingContest extends GameEvent {

    public FishingContest(final String name, final String description, final int duration) {
        super(name, description, duration);
    }


    @Override
    public void start() {

    }

    @Override
    public void end() {
    }
}
