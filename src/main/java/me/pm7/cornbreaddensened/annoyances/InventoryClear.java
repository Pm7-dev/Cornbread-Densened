package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class InventoryClear implements Listener {
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent e) {
        for(int i=0; i<35; i++) { if(e.getInventory().getItem(i) == null) {return;} }
        for(int in=0; in<10; in++) { e.getInventory().getItem((int) Math.floor(Math.random() * (36))).setType(Material.AIR); }
        Player p = (Player) e.getInventory().getHolder();
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Something happened. Figure it out"));
        e.getItem().getItemStack().setType(Material.AIR);
    }
}
