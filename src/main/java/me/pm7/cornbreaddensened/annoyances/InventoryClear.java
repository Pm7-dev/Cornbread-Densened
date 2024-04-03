package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.logging.Level;

public class InventoryClear implements Listener {
    Random random = new Random();
    CornbreadDensened plugin = CornbreadDensened.getPlugin();
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) {return;}
        Player p = (Player) e.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            for(int i=0; i<36; i++) { if(p.getInventory().getItem(i) == null) { return; } }
            for(int in=0; in<12; in++) { p.getInventory().setItem((int) Math.floor(random.nextFloat() * (36)), null); }
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Your inventory was looking a little full, so I cleared some stuff"));
            e.getItem().getItemStack().setType(Material.AIR);
        }, 3L);
    }
}
