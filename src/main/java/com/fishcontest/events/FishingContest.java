package com.fishcontest.events;

import com.fishcontest.Main;
import com.fishcontest.config.FishContestConfig;
import com.fishcontest.obj.EventLeaderboard;
import com.fishcontest.obj.FishingLeaderboard;
import com.fishcontest.tasks.FishContestRunnable;
import com.fishcontest.utils.ColorsUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FishingContest extends GameEvent {

    private final Map<Player, Integer> playerScores;

    public FishingContest(FishContestConfig config) {
        super("Fish Contest", config.getDescription(), config.getDuration());
        this.playerScores = new HashMap<>();
    }

    public Map<Player, Integer> getPlayerScores() {
        return playerScores;
    }

    @Override
    public void start(final Main main) {
        new FishContestRunnable(this).runTaskTimer(main, 0, 20);
        this.isPlaying = true;
    }

    @Override
    public void end(EventLeaderboard leaderboard) {
        FishingLeaderboard fishingLeaderboard = (FishingLeaderboard) leaderboard;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(fishingLeaderboard.asString());

            player.sendMessage(ColorsUtil.translate.apply(String.format("&eYour position: #%s", fishingLeaderboard.getIndex(player))));
        }
        this.isPlaying = false;
    }

    public FishingLeaderboard getLeaderboard() {
        return new FishingLeaderboard(playerScores);
    }

}
