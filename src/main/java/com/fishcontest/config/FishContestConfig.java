package com.fishcontest.config;

import org.bukkit.configuration.file.FileConfiguration;

public class FishContestConfig {

    private final FileConfiguration fileConfiguration;
    public FishContestConfig(FileConfiguration fileConfiguration){
        this.fileConfiguration = fileConfiguration;
    }

    public String getDescription(){
        return fileConfiguration.getString("fish-contest.description");
    }

    public int getDuration(){
        return fileConfiguration.getInt("fish-contest.duration");
    }

    public int getReward(){
        return fileConfiguration.getInt("fish-contest.reward");
    }
}
