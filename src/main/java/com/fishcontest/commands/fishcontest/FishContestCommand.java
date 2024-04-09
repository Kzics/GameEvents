package com.fishcontest.commands.fishcontest;

import com.fishcontest.Main;
import com.fishcontest.commands.CommandBase;
import com.fishcontest.commands.fishcontest.subcommands.AddRewardCommand;
import com.fishcontest.commands.fishcontest.subcommands.StartCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FishContestCommand extends CommandBase implements TabCompleter {

    public FishContestCommand(final Main main){
        registerSubCommand("start", new StartCommand(main));
        registerSubCommand("addreward", new AddRewardCommand(main));
        //registerSubCommand("help");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(Arrays.asList("addreward","start"));
        }
        return null;
    }

}
