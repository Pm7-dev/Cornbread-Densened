package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class SpawnThingsAroundPlayers {
    static Random random = new Random();
    static int tick = 0;
    static int maxTick = 2400;
    public static void Run() {
        if(tick < maxTick) {tick+=1; return;}

        //spawn a random of one of the cool mobs with resistance 5 for 10 seconds so it can fall normally
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(!p.getWorld().getName().equals("world")) { return; }
            if(Math.floor(Math.random()*2)!=1) { continue; }
            Location loc = p.getLocation().clone();
            loc.setY(130);
            switch((int) (random.nextFloat()*5)) {
                case 0: {
                    loc.add((random.nextFloat()*60)-30, 0, (random.nextFloat()*60)-30);
                    Ghast entity = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
                    Ghast entity1 = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    break;
                }
                case 1: {
                    loc.add((random.nextFloat()*60)-30, 0, (random.nextFloat()*60)-30);
                    Blaze entity = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    Blaze entity1 = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    Blaze entity2 = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity2.setTarget(p);
                    break;
                }
                case 2: {
                    loc.add((random.nextFloat()*60)-30, 0, (random.nextFloat()*60)-30);
                    MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
                    MagmaCube entity1 = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity.setSize(7);
                    entity1.setSize(5);
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    entity1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    break;
                }
                case 3: {
                    loc.add((random.nextFloat()*60)-30, 0, (random.nextFloat()*60)-30);
                    Phantom entity = (Phantom) loc.getWorld().spawnEntity(loc, EntityType.PHANTOM);
                    Phantom entity1 = (Phantom) loc.getWorld().spawnEntity(loc, EntityType.PHANTOM);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity.setSize(8);
                    entity1.setSize(9);
                    break;
                }
                case 4: {
                    loc.add((random.nextFloat()*60)-30, 0, (random.nextFloat()*60)-30);
                    Ravager entity = (Ravager) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
                    Ravager entity1 = (Ravager) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    entity1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    break;
                }
            }
        }

        maxTick = (int) Math.floor(random.nextFloat()*(1200+6700)+1344);
        tick = 0;
    }
}
