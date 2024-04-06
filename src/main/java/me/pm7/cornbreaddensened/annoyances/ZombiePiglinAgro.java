package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.List;

public class ZombiePiglinAgro implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if(e.getEntity().getType() != EntityType.ZOMBIFIED_PIGLIN) { return; }
        Mob piglin = (Mob) e.getEntity();
        List<Entity> nearby = piglin.getNearbyEntities(400, 400, 400);
        for(Entity entity : nearby) {
            if(!(entity instanceof Player)) { continue; }
            if(((Player) entity).getGameMode() != GameMode.SURVIVAL) { continue; }
            piglin.setTarget((LivingEntity) entity);
            return;
        }
    }
}
