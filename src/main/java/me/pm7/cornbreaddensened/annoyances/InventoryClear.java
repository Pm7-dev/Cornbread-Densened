package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class InventoryClear implements Listener {
    Random random = new Random();
    CornbreadDensened plugin = CornbreadDensened.getPlugin();
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) {return;}
        Player p = (Player) e.getEntity();
        if(p.getGameMode() != GameMode.SURVIVAL) { return; }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            int count = 0;
            for(int i=0; i<36; i++) { if(p.getInventory().getItem(i) != null) { count++; } }
            if(count < 29) { return; }
            for(int in=0; in<12; in++) { p.getInventory().setItem((int) Math.floor(random.nextFloat() * (36)), null); }
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Your inventory was looking a little full, so I cleared some stuff"));
        }, 3L);
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player p = (Player) e.getInventory().getHolder();
        if(p.getGameMode() != GameMode.SURVIVAL) { return; }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            int count = 0;
            for(int i=0; i<36; i++) { if(p.getInventory().getItem(i) != null) { count++; } }
            if(count < 29) { return; }
            for(int in=0; in<12; in++) { p.getInventory().setItem((int) Math.floor(random.nextFloat() * (36)), null); }
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Your inventory was looking a little full, so I cleared some stuff"));
        }, 3L);
    }
}
