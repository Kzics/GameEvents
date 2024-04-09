package com.fishcontest.events;

import com.fishcontest.Main;
import com.fishcontest.config.FishContestConfig;
import com.fishcontest.obj.EventLeaderboard;
import com.fishcontest.obj.FishingLeaderboard;
import com.fishcontest.tasks.FishContestRunnable;
import com.fishcontest.utils.ColorsUtil;
import com.fishcontest.utils.DefaultFontInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishingContest extends GameEvent {
    private final Map<Player, Integer> playerScores;
    private final FishContestConfig config;
    public FishingContest(FishContestConfig config) {
        super("Fish Contest", config.getDescription(), config.getDuration());
        this.playerScores = new HashMap<>();
        this.config = config;
    }


    public FishingContest addReward(ItemStack itemStack){
        if(config.getRewards() == null){
            config.setReward(new ArrayList<>());
        }
        config.getRewards().add(itemStack);

        return this;
    }

    public FishContestConfig getConfig() {
        return config;
    }

    public Map<Player, Integer> getPlayerScores() {
        return playerScores;
    }

    @Override
    public void start(final Main main) {
        new FishContestRunnable(this).runTaskTimer(main, 0, 20);
        for (Player player : Bukkit.getOnlinePlayers()){
            playerScores.put(player, 0);
        }
        this.isPlaying = true;
    }

    @Override
    public void end(EventLeaderboard leaderboard) {
        FishingLeaderboard fishingLeaderboard = (FishingLeaderboard) leaderboard;

        for (Player player : Bukkit.getOnlinePlayers()) {
            DefaultFontInfo.sendCenteredMessage(player,fishingLeaderboard.asString());

            DefaultFontInfo.sendCenteredMessage(player, ColorsUtil.translate.apply(String.format("&eYour position: #%s", fishingLeaderboard.getIndex(player))));
            player.sendMessage(" ");
        }
        this.isPlaying = false;

        for (int i = 1; i <4; i++){
            if(fishingLeaderboard.asList().size() < i) break;

            Player player = ((FishingLeaderboard) leaderboard).getPlayer(i);
            player.sendMessage(ColorsUtil.translate.apply("&eYou have won a prize!"));
            player.getInventory().addItem(config.getRewards().get(i-1));
        }
    }

    public FishingLeaderboard getLeaderboard() {
        return new FishingLeaderboard(playerScores);
    }

}
