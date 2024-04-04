package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
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
            if(Math.floor(Math.random()*3)!=1) { continue; }
            Location loc = p.getLocation().clone();
            switch((int) (random.nextFloat()*5)) {
                case 0: {
                    loc.add((random.nextFloat()*200)-100, 0, (random.nextFloat()*200)-100);
                    loc.setY(250);
                    Ghast entity = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
                    loc.setX((random.nextFloat()*200)-100);
                    loc.setZ((random.nextFloat()*200)-100);
                    Ghast entity1 = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    break;
                }
                case 1: {
                    loc.add((random.nextFloat()*100)-50, 0, (random.nextFloat()*100)-50);
                    loc.setY(250);
                    Blaze entity = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    Blaze entity1 = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    loc.setX((random.nextFloat()*100)-50);
                    loc.setZ((random.nextFloat()*100)-50);
                    Blaze entity2 = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    Blaze entity3 = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity2.setTarget(p);
                    entity3.setTarget(p);
                    break;
                }
                case 2: {
                    loc.add((random.nextFloat()*100)-50, 0, (random.nextFloat()*100)-50);
                    loc.setY(250);
                    MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
                    entity.setTarget(p);
                    entity.setSize(7);
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    break;
                }
                case 3: {
                    loc.add((random.nextFloat()*100)-50, 0, (random.nextFloat()*100)-50);
                    loc.setY(250);
                    Phantom entity = (Phantom) loc.getWorld().spawnEntity(loc, EntityType.PHANTOM);
                    entity.setTarget(p);
                    entity.setSize(7);
                    break;
                }
                case 4: {
                    loc.add((random.nextFloat()*70)-35, 0, (random.nextFloat()*70)-35);
                    loc.setY(250);
                    Ravager entity = (Ravager) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
                    loc.setX((random.nextFloat()*200)-100);
                    loc.setZ((random.nextFloat()*200)-100);
                    Ravager entity1 = (Ravager) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
                    entity.setTarget(p);
                    entity1.setTarget(p);
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
                    break;
                }
            }
        }

        maxTick = (int) Math.floor(random.nextFloat()*(1200+8800)+1344);
        tick = 0;
    }
}
