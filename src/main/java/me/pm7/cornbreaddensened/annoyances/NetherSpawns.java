package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class NetherSpawns implements Listener {
    Random random = new Random();

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {

        // When in the nether, spawn an endermite alongside each living entity that spawns, as long as they are not an endermite
        if(!e.getEntity().getWorld().getName().equals("world_nether")) { return; }
        if(e.getEntity().getType() != EntityType.ENDERMITE && e.getEntity() instanceof LivingEntity) {
            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);

            // Spawn an extra endermite for each blaze
            if(e.getEntity().getType() == EntityType.BLAZE) {
                e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
                return;
            }

            // Might as well spawn yet another one every once in a while just for fun
            if(Math.floor(random.nextFloat() * (3)) == 1) {
                e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ENDERMITE);
            }
        }
    }
}
