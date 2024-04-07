package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class LessKnockback implements Listener {
    static CornbreadDensened plugin = CornbreadDensened.getPlugin();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        if(e.getDamager() instanceof Player && !(entity instanceof Player)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> entity.setVelocity(e.getDamager().getLocation().getDirection().multiply(-0.25f).setY(0.3)), 1L);
        }
    }
}
