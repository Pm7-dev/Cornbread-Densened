package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.List;

public class NoEndBed implements Listener {
    List<Material> bedsList = Arrays.asList(Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED,
            Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIME_BED, Material.RED_BED,
            Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.PINK_BED, Material.PURPLE_BED,
            Material.WHITE_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.MAGENTA_BED, Material.RESPAWN_ANCHOR);
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getBlock().getWorld().getName().equals("world_the_end")) {
            if(bedsList.contains(e.getBlockPlaced().getType())) {
                e.setCancelled(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Imagine I let you use bed explosions in a world full of wool"));
                e.getPlayer().setHealth(0.0f);
            }
        }
    }
}
