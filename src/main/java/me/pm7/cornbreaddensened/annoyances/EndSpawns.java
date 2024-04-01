package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.util.Vector;

public class EndSpawns implements Listener {
    @EventHandler
    public void onEndSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        if(world.getName().equals("world_the_end") && entity.getType() == EntityType.ENDERMAN) {
            Location loc = entity.getLocation();

            // Spawn a normal, basic ravager
            if((int) Math.floor(Math.random() * (30)) == 1) {
                world.spawnEntity(loc, EntityType.RAVAGER);
            }
            // Spawn a not so normal, not so basic ravager
            if((int) Math.floor(Math.random() * (30)) == 1) {
                Entity bee = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.BEE);
                Entity ravager = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.RAVAGER);
                bee.addPassenger(ravager);
            }
            // Spawn a Bardian (Or Guee)
            if((int) Math.floor(Math.random() * (30)) == 1) {
                Entity bee = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.BEE);
                Entity guardian = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.GUARDIAN);
                bee.addPassenger(guardian);
            }
        }
    }
}
