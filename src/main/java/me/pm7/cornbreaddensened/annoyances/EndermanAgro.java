package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class EndermanAgro implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getPlayer().getWorld().getName().equals("world_the_end")) { return; }
        List<Entity> nearby = e.getPlayer().getNearbyEntities(12, 10, 12);
        for(Entity entity : nearby) {
            if (entity.getType() != EntityType.ENDERMAN) { continue; }
            if(((Enderman) entity).getTarget() != null) { continue; }
            ((Enderman) entity).setTarget(e.getPlayer());
        }
    }
}
