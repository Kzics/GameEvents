package com.fishcontest.commands.fishcontest.subcommands;

import com.fishcontest.Main;
import com.fishcontest.commands.ICommand;
import com.fishcontest.events.FishingContest;
import com.fishcontest.utils.ColorsUtil;
import org.bukkit.command.CommandSender;

public class EndCommand implements ICommand {

    private final Main main;
    public EndCommand(final Main main){
        this.main = main;
    }
    @Override
    public String getName() {
        return "end";
    }

    @Override
    public String getDescription() {
        return "End the contest";
    }

    @Override
    public String getPermission() {
        return "gameevents.fishcontest.end";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        FishingContest fishingContest = main.getEventsManager().getEvent(FishingContest.class);
        if(fishingContest == null){
            sender.sendMessage(ColorsUtil.translate.apply("&cThere is no contest running!"));
            return;
        }

        fishingContest.end(fishingContest.getLeaderboard());

        sender.sendMessage(ColorsUtil.translate.apply("&aSucessfully ended the contest!"));
    }
}
