package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class NoBreakingNetherrack implements Listener {
    Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE && e.getBlock().getWorld().getName().equals("world_nether")) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            if((int)Math.floor(random.nextFloat() * 6) == 1) {
                e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5), EntityType.ENDERMITE);
            }
        }
        if(e.getBlock().getBlockData().getMaterial() == Material.NETHERRACK) {
            e.setCancelled(true);
            e.getBlock().setType(Material.DEEPSLATE);
        }
    }
}
