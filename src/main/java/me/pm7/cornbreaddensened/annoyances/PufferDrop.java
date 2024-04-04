package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PufferDrop {
    static Random random = new Random();
    static int tick = 0;
    public static void Run() {
        if(tick<2400) {tick+=1; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(random.nextDouble() > 0.65d) {
                LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation().clone().add(-0.65, 10, 0.3), EntityType.PUFFERFISH);
                LivingEntity entity2 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation().clone().add(+0.27, 10, -0.43), EntityType.PUFFERFISH);
                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5);
                entity.addPotionEffect(effect);
                entity2.addPotionEffect(effect);
            }
        }
        tick = 0;
    }
}
