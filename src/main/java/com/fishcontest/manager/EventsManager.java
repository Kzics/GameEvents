package com.fishcontest.manager;

import com.fishcontest.Main;
import com.fishcontest.events.GameEvent;
import com.fishcontest.utils.ColorsUtil;
import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;

public class EventsManager {


    private final Main main;
    private final Set<GameEvent> activeEvents;
    public EventsManager(final Main main){
        this.main = main;
        this.activeEvents = new HashSet<>();
    }


    private Set<GameEvent> getActiveEvents() {
        return activeEvents;
    }

    public void addEvent(GameEvent event){
        activeEvents.add(event);
    }

    public void removeEvent(GameEvent event){
        activeEvents.remove(event);
    }

    public void startEvent(GameEvent event){
        event.start(main);
        activeEvents.add(event);

        Bukkit.broadcastMessage(ColorsUtil.translate.apply("&e&lFish Contest | &7The event has started!"));
    }

    public boolean hasEvent(Class<? extends GameEvent> event){
        for(GameEvent e : activeEvents){
            if(e.getClass().equals(event)){
                return true;
            }
        }
        return false;
    }

    public GameEvent getEvent(Class<? extends GameEvent> event){
        for(GameEvent e : activeEvents){
            if(e.getClass().equals(event)){
                return e;
            }
        }
        return null;
    }
}
