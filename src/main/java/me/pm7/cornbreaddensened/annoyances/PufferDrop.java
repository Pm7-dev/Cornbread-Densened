package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PufferDrop {
    static Random random = new Random();
    static int tick = 0;
    static int needed = (int) Math.floor((random.nextFloat() * (6500 - 5000)) + 5000);
    public static void Run() {
        if(tick < needed) { tick+=1; return; }
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(random.nextDouble() > 0.65d) {
                LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation().clone().add(-0.65, 18, 0.3), EntityType.PUFFERFISH);
                LivingEntity entity2 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation().clone().add(+0.27, 18, -0.43), EntityType.PUFFERFISH);
                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5);
                entity.addPotionEffect(effect);
                entity2.addPotionEffect(effect);
            }
        }
        tick = 0;
        needed = (int) Math.floor((random.nextFloat() * (6500 - 5000)) + 5000);
    }
}
