package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.IdlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoStandingStill implements Listener {
    static List<IdlePlayer> idlePlayers = new ArrayList<>();
    static Random random = new Random();

    public static void Run() {

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(MathPrompt.isStudent(p)) { return; }
            if(!isIdle(p)) { idlePlayers.add(new IdlePlayer(p, 0, false)); }
            IdlePlayer idle = getIdle(p);
            if(idle == null) { continue; }
            if(hasNoGUIOpen(p)) {
                idle.ticks = 0;
            } else {
                idle.ticks++;
            }

            if(idle.ticks >= 80) {
                if(Math.floor(random.nextFloat() * 30) == 0) {
                    idle.dead = true;
                    idle.player.setHealth(0.0d);
                    idle.ticks = 0;
                }
            }
        }
    }

    private static boolean isIdle(Player p) {
        for(IdlePlayer plr : idlePlayers) {
            if(plr.player == p) { return true; }
        }
        return false;
    }

    private static IdlePlayer getIdle(Player p) {
        for(IdlePlayer plr : idlePlayers) {
            if(plr.player == p) { return plr; }
        }
        return null;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        IdlePlayer p = getIdle(e.getEntity());
        if(p == null) { return; }
        if(p.dead) {
            e.setDeathMessage(p.player.getDisplayName() + " died of... being boring for too long, idk");
            p.dead = false;
            p.ticks = 0;
        }
    }

    public static boolean hasNoGUIOpen(Player player) {
        InventoryView openInventory = player.getOpenInventory();
        Inventory topInventory = openInventory.getTopInventory();
        Inventory playerInventory = player.getInventory();

        // Check if the top inventory is the player's own inventory
        return topInventory == null || topInventory.equals(playerInventory);
    }

    //todo: TRY SOUT ON THIS InventoryOpenEvent

    @EventHandler
    public void onPlayerInv(InventoryOpenEvent e) {
        System.out.println(e.getView().getTitle());
    }
}
