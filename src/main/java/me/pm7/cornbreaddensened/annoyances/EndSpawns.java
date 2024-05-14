package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
                ((Ravager) ravager).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 0, true));
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
            }
            // Spawn a Witch
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.WITCH);
            }
            // Spawn a Wither Skeleton
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.WITHER_SKELETON);
            }
            // Spawn stray
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.STRAY);
            }
            // Spawn Vindicator
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                Vindicator vindicator = (Vindicator) world.spawnEntity(loc, EntityType.VINDICATOR);
                vindicator.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, PotionEffect.INFINITE_DURATION, 0, true));
            }
            // Spawn Pillager
            if((int) Math.floor(random.nextFloat() * (16)) == 1) {
                world.spawnEntity(loc, EntityType.PILLAGER);
            }
        }
    }
}
