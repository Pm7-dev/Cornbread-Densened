package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class NetherPhantoms {
    static Random random = new Random();
    static int tick = 0;
    public static void Run() {
        if(tick<2400) {tick+=1; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(random.nextDouble() > 0.65d) {
                Phantom entity = (Phantom) p.getWorld().spawnEntity(p.getLocation().clone().add(0, 13, 0), EntityType.PHANTOM);
                entity.setSize((int) Math.floor(random.nextFloat() * 4) + 2);
            }
        }
        tick = 0;
    }
}
