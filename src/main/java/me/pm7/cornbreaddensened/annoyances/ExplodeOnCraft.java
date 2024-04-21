package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ExplodeOnCraft implements Listener {
    @EventHandler
    public void onPlayerCraft(PlayerInteractEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if(e.getClickedBlock().getBlockData().getMaterial() == Material.CRAFTING_TABLE) {
            Location location = e.getClickedBlock().getLocation();
            if ((int) Math.floor(Math.random() * (15)) == 1) {
                location.getWorld().createExplosion(location, 3, true);
            }
        }
    }
}