package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RandomNetherSpeed implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getWorld().getName().equals("world_nether")) {
            if((int) Math.floor(Math.random() * (2000)) == 1) {
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 100, 40, false);
                p.addPotionEffect(potionEffect);
            }
        }
    }
}