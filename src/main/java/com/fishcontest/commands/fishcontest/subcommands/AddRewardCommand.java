package com.fishcontest.commands.fishcontest.subcommands;

import com.fishcontest.Main;
import com.fishcontest.commands.ICommand;
import com.fishcontest.events.FishingContest;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AddRewardCommand implements ICommand {

    private final Main main;
    public AddRewardCommand(final Main main){
        this.main = main;
    }
    @Override
    public String getName() {
        return "addreward";
    }

    @Override
    public String getDescription() {
        return "Add a reward to the Fish Contest";
    }

    @Override
    public String getPermission() {
        return "gameevents.fishcontest.addreward";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage("You must be a player to execute this command.");
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType().equals(Material.AIR)) return;


        List<ItemStack> rewards = (List<ItemStack>) main.getConfig().getList("fish-contest.rewards");
        if(rewards == null){
            rewards = new ArrayList<>();
        }
        rewards.add(itemStack);

        main.getConfig().set("fish-contest.rewards", rewards);

        main.saveConfig();
        player.sendMessage("Reward added to the Fish Contest!");
    }

}
