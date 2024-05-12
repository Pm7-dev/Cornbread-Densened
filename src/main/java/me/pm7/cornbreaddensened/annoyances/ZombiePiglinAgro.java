package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class ZombiePiglinAgro implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getPlayer().getWorld() != Bukkit.getWorld("world_nether")) { return; }
        List<Entity> nearby = e.getPlayer().getNearbyEntities(17, 200, 17);
        for(Entity entity : nearby) {
            if (entity.getType() != EntityType.ZOMBIFIED_PIGLIN) { continue; }
            if(((PigZombie) entity).getTarget() != null) { continue; }
            ((PigZombie) entity).setTarget(e.getPlayer());
        }
    }
}
