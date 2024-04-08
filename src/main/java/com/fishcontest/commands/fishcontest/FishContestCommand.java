package com.fishcontest.commands.fishcontest;

import com.fishcontest.Main;
import com.fishcontest.commands.CommandBase;
import com.fishcontest.commands.fishcontest.subcommands.StartCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FishContestCommand extends CommandBase {

    public FishContestCommand(final Main main){
        registerSubCommand("start", new StartCommand());
        //registerSubCommand("help");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}
