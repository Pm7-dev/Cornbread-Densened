package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class EndEndermanAgro implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if(e.getEntity().getType() != EntityType.ENDERMAN) { return; }
        Mob enderman = (Mob) e.getEntity();
        List<Entity> nearby = enderman.getNearbyEntities(400, 400, 400);
        for(Entity entity : nearby) {
            if(!(entity instanceof Player)) { continue; }
            if(((Player) entity).getGameMode() != GameMode.SURVIVAL) { continue; }
            enderman.setTarget((LivingEntity) entity);
            return;
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        List<Entity> nearby = e.getPlayer().getNearbyEntities(75, 200, 75);
        for(Entity entity : nearby) {
            if (entity.getType() != EntityType.ENDERMAN) { continue; }
            if(((Enderman) entity).getTarget() != null) { continue; }
            ((Enderman) entity).setTarget(e.getPlayer());
        }
    }
}
