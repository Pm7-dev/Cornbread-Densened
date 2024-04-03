package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class EndSpawns implements Listener {
    Random random = new Random();
    @EventHandler
    public void onEndSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        if(world.getName().equals("world_the_end") && entity.getType() == EntityType.ENDERMAN) {
            Location loc = entity.getLocation();
            // Spawn a normal, basic ravager
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.RAVAGER); }
            // Spawn a not so normal, not so basic ravager
            if((int) Math.floor(random.nextFloat() * (21)) == 1) {
                Entity bee = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.BEE);
                Entity ravager = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.RAVAGER);
                bee.addPassenger(ravager);
            }
            // Spawn a Guardian
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.ELDER_GUARDIAN); }
            // Spawn a Vex
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.VEX); }
            // Spawn a Cave Spider
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.CAVE_SPIDER); }
            // Spawn a Witch
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.WITCH); }
            // Spawn a Wither Skeleton
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.WITHER_SKELETON); }
            // Spawn stray
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.STRAY); }
            // Spawn Silverfish
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.SILVERFISH); world.spawnEntity(loc, EntityType.SILVERFISH); }
            // Spawn Phantom
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.PHANTOM); }
            // Spawn Vindicator
            if((int) Math.floor(random.nextFloat() * (21)) == 1) { world.spawnEntity(loc, EntityType.VINDICATOR); }
        }
    }
}
