package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class Hoglins implements Listener {
    static Random random = new Random();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if(e.getEntity().getType() != EntityType.HOGLIN || e.getEntity().getType() != EntityType.ZOGLIN) { return; }
        e.setCancelled(true);
        if(random.nextBoolean()) { e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.RAVAGER); }
        else { e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.WITCH); }
    }
}
