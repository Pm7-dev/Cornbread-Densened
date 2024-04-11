package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class NoOverworldDrops implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if(e.getEntityType() == EntityType.BLAZE) {
            if(e.getEntity().getWorld().getName().equals("world")) {
                e.getDrops().clear();
            }
        }
    }
}
