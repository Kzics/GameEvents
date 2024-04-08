package com.fishcontest;

import com.fishcontest.manager.EventsManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {




    private EventsManager eventsManager;
    @Override
    public void onEnable() {
        this.eventsManager = new EventsManager(this);
        getLogger().info("Fish Contest plugin has been enabled!");
    }

    public EventsManager getEventsManager() {
        return eventsManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("Fish Contest plugin has been disabled!");
    }
}
