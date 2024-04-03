package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class NetherSpawns implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntity().getType() != EntityType.ENDERMITE) {
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
        }
    }
}
