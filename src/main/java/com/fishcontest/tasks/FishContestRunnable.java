package com.fishcontest.tasks;

import com.fishcontest.Main;
import com.fishcontest.events.FishingContest;
import com.fishcontest.obj.FishingLeaderboard;
import com.fishcontest.utils.ColorsUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FishContestRunnable extends BukkitRunnable {
    private final FishingContest fishingContest;
    private int duration;
    private Player player;

    public FishContestRunnable(FishingContest fishingContest) {
        this.fishingContest = fishingContest;
        this.duration = 0;
    }

    @Override
    public void run() {

        FishingLeaderboard leaderboard = fishingContest.getLeaderboard();

        for (Player player : Bukkit.getOnlinePlayers()) {
            int playerIndex = fishingContest.getLeaderboard().getIndex(player);

            player.sendActionBar(ColorsUtil.translate.apply(String.format("&eScore: %s (Position #%s)",
                    fishingContest.getLeaderboard().getScore(playerIndex), leaderboard.getIndex(player))));
        }

        Player currentKing = null;
        if (!leaderboard.asList().isEmpty()) {
            currentKing = leaderboard.getPlayer(1);
        }

        if (currentKing != null) {
            if (player != null && !player.getUniqueId().equals(currentKing.getUniqueId())) {
                player.setGlowing(false);
            }
            player = currentKing;
            player.setGlowing(true);
        }

        if (duration >= fishingContest.getDuration()) {
            fishingContest.end(leaderboard);
            cancel();
            return;
        }
        duration++;
    }
}
