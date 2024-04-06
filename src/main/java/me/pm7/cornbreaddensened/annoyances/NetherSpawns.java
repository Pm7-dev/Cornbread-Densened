package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class NetherSpawns implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(!e.getEntity().getWorld().getName().equals("world_nether")) { return; }
        if(e.getEntity().getType() != EntityType.ENDERMITE && e.getEntity() instanceof LivingEntity) {
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
        }
    }
}
