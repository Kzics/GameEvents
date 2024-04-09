package com.fishcontest.obj;

import com.fishcontest.utils.ColorsUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishingLeaderboard extends EventLeaderboard {

    private final List<ScoreEntry> leaderboard;

    public FishingLeaderboard(Map<Player, Integer> playerScores) {
        this.leaderboard = new ArrayList<>();
        playerScores.entrySet().stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                .forEach(entry -> leaderboard.add(new ScoreEntry(entry.getKey(), entry.getValue())));
    }

    public int getIndex(Player player) {
        for (int i = 0; i < leaderboard.size(); i++) {
            if (leaderboard.get(i).player().equals(player)) {
                return i + 1;
            }
        }
        return -1;
    }
    public int getScore(int rank) {
        int index = rank - 1;
        if (index >= 0 && index < leaderboard.size()) {
            return leaderboard.get(index).score();
        }
        return -1;
    }

    public Player getPlayer(int rank) {
        int index = rank - 1;
        if (index >= 0 && index < leaderboard.size()) {
            return leaderboard.get(index).player();
        }
        return null;
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if(i >= leaderboard.size()) break;
            ScoreEntry entry = leaderboard.get(i);
            sb.append(i + 1).append(". ")
                    .append(entry.player().getName()).append(": ")
                    .append(entry.score()).append("\n");
        }
        return ColorsUtil.translate.apply("&e" + sb);
    }

    public List<String> asList(){
        List<String> list = new ArrayList<>();
        for (ScoreEntry entry : leaderboard) {
            list.add(entry.player().getName() + ": " + entry.score());
        }
        return list;
    }

    private record ScoreEntry(Player player, Integer score) {
    }
}



