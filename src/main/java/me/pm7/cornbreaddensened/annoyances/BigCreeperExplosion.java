package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.potion.PotionEffectType;

public class BigCreeperExplosion implements Listener {
    @EventHandler
    public void makeCreeperBoomBoom(EntityExplodeEvent e) {
        if(e.getEntity().getType() == EntityType.CREEPER) {
            e.setCancelled(true);
            LivingEntity entity = (LivingEntity) e.getEntity();
            entity.removePotionEffect(PotionEffectType.SPEED);
            entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            entity.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            entity.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            e.getLocation().getWorld().createExplosion(e.getLocation(), 13, true);
        }
    }
}
