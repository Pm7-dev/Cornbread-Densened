package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoCrouch implements Listener {
    @EventHandler
    public void onPlayerCrouch(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE) {return;}
        if(p.isSneaking()) {
            e.setCancelled(true);
            p.setFireTicks(200);
        }
    }
}