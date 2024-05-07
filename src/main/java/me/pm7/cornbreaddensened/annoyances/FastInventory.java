package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.BoringPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FastInventory implements Listener {
    static List<BoringPlayer> boringPlayers = new ArrayList<>();
    static Random random = new Random();

    public static void Run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            BoringPlayer boring = getNerd(p);

            if(boring.isBoring) { boring.ticks +=1; } else { boring.ticks = 0; }

            if(boring.ticks >= 65) {
                if(Math.floor(random.nextFloat() * 20) == 0) {
                    boring.dead = true;
                    boring.player.setHealth(0.0d);
                    boring.ticks = 0;
                }
            }
        }
    }

    private static BoringPlayer getNerd(Player p) {
        for(BoringPlayer plr : boringPlayers) {
            if(plr.player == p) { return plr; }
        }
        BoringPlayer boringPlayer = new BoringPlayer(p, 0, false, false);
        boringPlayers.add(boringPlayer);
        return boringPlayer;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        BoringPlayer boring = getNerd(e.getEntity());
        if(boring.dead) {
            e.setDeathMessage(boring.player.getDisplayName() + " died of taking too long in the menu");
            boring.dead = false;
            boring.isBoring = false;
            boring.ticks = 0;
        }
    }

    @EventHandler
    public void onPlayerCloseInv(InventoryCloseEvent e) {
        BoringPlayer boring = getNerd((Player) e.getPlayer());
        boring.isBoring = false;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        BoringPlayer boring = getNerd(e.getPlayer());
        boring.isBoring = false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        BoringPlayer boring = getNerd((Player) e.getWhoClicked());
        boring.isBoring = true;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        BoringPlayer boring = getNerd((Player) e.getPlayer());
        boring.isBoring = true;
    }
}
