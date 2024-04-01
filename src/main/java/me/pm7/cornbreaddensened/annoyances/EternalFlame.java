package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EternalFlame implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                Player p = (Player) e.getEntity();
                p.setFireTicks(200);
            }
        }
    }
}
