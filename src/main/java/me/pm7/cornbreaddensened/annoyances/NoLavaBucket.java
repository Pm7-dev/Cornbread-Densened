package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class NoLavaBucket implements Listener {
    @EventHandler
    public void onPlayerUseBucket(PlayerBucketFillEvent e) {
        if(e.getBlock().getType() == Material.LAVA) {
            e.setCancelled(true);
            e.getPlayer().setFireTicks(200);
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Ouch! Too hot..."));
            e.getPlayer().getInventory().setItemInMainHand(null);
        }
    }
}
