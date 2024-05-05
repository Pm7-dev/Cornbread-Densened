package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftChest implements Listener {
    @EventHandler
    public void onPlayerCraft(CraftItemEvent e) {
        if(e.getWhoClicked().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getCurrentItem().getType() == Material.CHEST || e.getCurrentItem().getType() == Material.BARREL) {
            e.setCurrentItem(new ItemStack(Material.ALLIUM));
            ((Player) e.getWhoClicked()).spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "I would totally make these explode, but I'm being nice this time."));
            e.getWhoClicked().closeInventory();
        }
    }
}
