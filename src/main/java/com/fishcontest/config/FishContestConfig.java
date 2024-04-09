package com.fishcontest.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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

    public List<ItemStack> getRewards(){
        return (List<ItemStack>) fileConfiguration.getList("fish-contest.rewards");
    }

    public int getWinnersAmount(){
        return fileConfiguration.getInt("fish-contest.winners");
    }
}
