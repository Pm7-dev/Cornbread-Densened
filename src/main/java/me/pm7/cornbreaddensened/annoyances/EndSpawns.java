package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
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
            // Spawn a not so normal, not so basic ravager
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                Entity bee = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.BEE);
                Entity ravager = world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.RAVAGER);
                bee.addPassenger(ravager);
            }
            // Spawn a Guardian
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.GUARDIAN);
                world.spawnEntity(loc, EntityType.GUARDIAN);
            }
            // Spawn a Vex
            if((int) Math.floor(random.nextFloat() * (16)) == 1) { world.spawnEntity(loc.clone().add(new Vector(0, 7, 0)), EntityType.VEX); }
            // Spawn a Cave Spider
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.CAVE_SPIDER);
                world.spawnEntity(loc, EntityType.CAVE_SPIDER);
                world.spawnEntity(loc, EntityType.CAVE_SPIDER);
            }
            // Spawn a Witch
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.WITCH);
                world.spawnEntity(loc, EntityType.WITCH);
            }
            // Spawn a Wither Skeleton
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.WITHER_SKELETON);
                world.spawnEntity(loc, EntityType.WITHER_SKELETON);
            }
            // Spawn stray
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.STRAY);
                world.spawnEntity(loc, EntityType.STRAY);
            }
            // Spawn Silverfish
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.SILVERFISH);
                world.spawnEntity(loc, EntityType.SILVERFISH);
            }
            // Spawn Phantom
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                Location newLoc = loc.clone().add(0, 40, 0);
                Phantom phantom1 = (Phantom) world.spawnEntity(newLoc, EntityType.PHANTOM);
                Phantom phantom2 = (Phantom) world.spawnEntity(newLoc, EntityType.PHANTOM);
                phantom1.setSize(5);
                phantom2.setSize(6);
            }
            // Spawn Vindicator
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.VINDICATOR);
                world.spawnEntity(loc, EntityType.VINDICATOR);
            }
            // Spawn More Silverfish
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.SILVERFISH);
                world.spawnEntity(loc, EntityType.SILVERFISH);
                world.spawnEntity(loc, EntityType.SILVERFISH);
            }
            // Spawn Pillager
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.PILLAGER);
                world.spawnEntity(loc, EntityType.PILLAGER);
            }
        }
    }
}
