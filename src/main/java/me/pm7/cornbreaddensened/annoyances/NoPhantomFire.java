package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoPhantomFire implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if(!(e.getEntity() instanceof LivingEntity)) { return; }
        LivingEntity entity = (LivingEntity) e.getEntity();
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION,0));
    }
}
