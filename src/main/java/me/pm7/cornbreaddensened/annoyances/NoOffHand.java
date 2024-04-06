package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class NoOffHand {
    static int tick = 0;

    public static void Run() {
        if(tick < 6) {tick++; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            Inventory inv = p.getInventory();
            if(inv.getItem(40) != null) {
                inv.setItem(40, null);
                p.closeInventory();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Your other hand was cut off in the accident."));
            }

            // other non offhand thing some armor thing probably
            if(inv.getItem(37) != null) {
                inv.setItem(37, null);
                p.closeInventory();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("No. I said so"));
            }
        }
        tick = 0;
    }
}
