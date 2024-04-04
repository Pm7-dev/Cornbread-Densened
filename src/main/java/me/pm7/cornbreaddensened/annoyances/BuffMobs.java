package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BuffMobs implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntityType() != EntityType.PLAYER && e.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) e.getEntity();
            entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, PotionEffect.INFINITE_DURATION, 0));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 0));
        }
    }
}
