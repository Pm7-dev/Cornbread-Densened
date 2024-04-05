package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BigCreeperExplosion implements Listener {
    @EventHandler
    public void makeCreeperBoomBoom(EntityExplodeEvent e) {
        if(e.getEntity().getType() == EntityType.CREEPER) {
            e.getLocation().getWorld().createExplosion(e.getLocation(), 13, true);
        }
    }
}
