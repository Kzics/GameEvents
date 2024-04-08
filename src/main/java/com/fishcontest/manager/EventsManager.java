package com.fishcontest.manager;

import com.fishcontest.Main;
import com.fishcontest.events.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsManager {


    private final Main main;
    private final List<GameEvent> activeEvents;
    public EventsManager(final Main main){
        this.main = main;
        this.activeEvents = new ArrayList<>();
    }


    private List<GameEvent> getActiveEvents() {
        return activeEvents;
    }

    public void addEvent(GameEvent event){
        activeEvents.add(event);
    }

    public void removeEvent(GameEvent event){
        activeEvents.remove(event);
    }

    public void startEvent(GameEvent event){
        event.start();
    }
}
