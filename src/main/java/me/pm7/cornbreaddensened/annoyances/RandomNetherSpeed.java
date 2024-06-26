package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomNetherSpeed implements Listener {
    Random random = new Random();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() != GameMode.SURVIVAL) { return; }
        if(p.getWorld().getName().equals("world_nether")) {
            if((int) Math.floor(random.nextFloat() * (2000)) == 1) {
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 100, 40, false);
                p.addPotionEffect(potionEffect);
            }
        }
    }
}