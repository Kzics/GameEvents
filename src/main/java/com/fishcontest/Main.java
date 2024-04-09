package com.fishcontest;

import com.fishcontest.commands.fishcontest.FishContestCommand;
import com.fishcontest.commands.fishcontest.subcommands.StartCommand;
import com.fishcontest.listeners.EventsListeners;
import com.fishcontest.manager.EventsManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.io.InputStreamFacade;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    private final Map<Player, Integer> playerScores = new HashMap<>();
    private EventsManager eventsManager;
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        this.eventsManager = new EventsManager(this);
        getLogger().info("Fish Contest plugin has been enabled!");

        getCommand("events").setExecutor(new FishContestCommand(this));

        try {
            copyStreamToFile(getResource("config.yml"), new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getServer().getPluginManager().registerEvents(new EventsListeners(this), this);
    }

    public EventsManager getEventsManager() {
        return eventsManager;
    }

    public void copyStreamToFile(InputStream source, File destination) throws IOException {
        try (OutputStream out = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = source.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } finally {
            if (source != null) {
                source.close();
            }
        }
    }

    public Map<Player, Integer> getPlayerScores() {
        return playerScores;
    }

    @Override
    public void onDisable() {
        getLogger().info("Fish Contest plugin has been disabled!");
    }
}
