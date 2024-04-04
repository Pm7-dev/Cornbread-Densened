package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class NoOffHand implements Listener {
    @EventHandler
    public void onInventoryChange(InventoryMoveItemEvent e) {
        if(e.getDestination().getItem(40) != null) {
            e.getDestination().setItem(40, null);
            ((Player) e.getDestination().getHolder()).spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Your other hand is uhh... broken... from the uhh... accident. Yeah that's it."));
        }
    }
}
