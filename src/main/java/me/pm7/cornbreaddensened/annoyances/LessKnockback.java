package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityKnockbackByEntityEvent;
import org.bukkit.util.Vector;

public class LessKnockback implements Listener {
    static CornbreadDensened plugin = CornbreadDensened.getPlugin();

    @EventHandler
    public void EntityKnockbackEvent(EntityKnockbackByEntityEvent e) {
        if(!(e.getEntity() instanceof Player) && e.getSourceEntity() instanceof Player) {
            e.setFinalKnockback(e.getFinalKnockback().multiply(-1));
        }
    }
}
