package com.fishcontest.listeners;

import com.fishcontest.Main;
import com.fishcontest.config.FishContestConfig;
import com.fishcontest.events.FishingContest;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EventsListeners implements Listener {

    private final Main main;
    private NamespacedKey fishKey;

    public EventsListeners(Main main){
        this.main = main;
        this.fishKey = new NamespacedKey(main, "fish");
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        final Player player = event.getPlayer();
        if(event.getCaught() == null) return;
        FishingContest contest = (FishingContest) main.getEventsManager().getEvent(FishingContest.class);
        if(contest == null) return;

        ItemStack caughtItem = ((Item)event.getCaught()).getItemStack();
        ItemMeta meta = caughtItem.getItemMeta();
        meta.getPersistentDataContainer().set(fishKey, PersistentDataType.STRING, "fish");
        caughtItem.setItemMeta(meta);


        if(event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
        }else{
            System.out.println("not a fish");
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        final Item droppedItem = event.getItemDrop();
        if(!(droppedItem.getItemStack().getItemMeta().getPersistentDataContainer().has(fishKey, PersistentDataType.STRING))) return;
        FishingContest contest = (FishingContest) main.getEventsManager().getEvent(FishingContest.class);
        if(contest == null) return;

        contest.getPlayerScores().put(player, contest.getPlayerScores().getOrDefault(player, 0) - 1);
    }


    @EventHandler
    public void onItemRetrieve(PlayerAttemptPickupItemEvent event){
        Player player = event.getPlayer();
        final Item retrievedItem = event.getItem();
        if(!(retrievedItem.getItemStack().getItemMeta().getPersistentDataContainer().has(fishKey, PersistentDataType.STRING))) return;
        FishingContest contest = (FishingContest) main.getEventsManager().getEvent(FishingContest.class);
        if(contest == null) return;

        contest.getPlayerScores().put(player, contest.getPlayerScores().getOrDefault(player, 0) + retrievedItem.getItemStack().getAmount());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        FishingContest contest = (FishingContest) main.getEventsManager().getEvent(FishingContest.class);
        if(contest == null) return;

        contest.getPlayerScores().put(player, 0);
    }
}

