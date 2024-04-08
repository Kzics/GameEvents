package com.fishcontest.commands.fishcontest.subcommands;

import com.fishcontest.Main;
import com.fishcontest.commands.ICommand;
import com.fishcontest.events.FishingContest;
import com.fishcontest.events.GameEvent;
import com.fishcontest.utils.ColorsUtil;
import org.bukkit.command.CommandSender;

public class StartCommand implements ICommand {


    private final Main main;
    public StartCommand(final Main main){
        this.main = main;
    }
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return ColorsUtil.translate.apply("Starts the Fish Contest");
    }

    @Override
    public String getPermission() {
        return "gameevents.fishcontest.start";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 1){
            sender.sendMessage(ColorsUtil.translate.apply("&cUsage: /fishcontest start <duration>"));
            return;
        }
        int duration = Integer.parseInt(args[0]);


        final String description = "Compete against other players to catch the most fish!";
        FishingContest fishingContest = new FishingContest("Fishing Contest", description ,duration);

        main.getEventsManager().startEvent(fishingContest);
    }
}
