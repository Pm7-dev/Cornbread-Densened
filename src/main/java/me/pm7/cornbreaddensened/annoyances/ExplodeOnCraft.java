package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ExplodeOnCraft implements Listener {
    @EventHandler
    public void onPlayerCraft(PlayerInteractEvent e) {
        if(e.getClickedBlock() != null) {
            if(e.getClickedBlock().getBlockData().getMaterial() == Material.CRAFTING_TABLE) {
                Location location = e.getClickedBlock().getLocation();
                if ((int) Math.floor(Math.random() * (15)) == 1) {
                    location.getWorld().createExplosion(location, 3, true);
                }
            }
        }
    }
}