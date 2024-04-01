package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class NoBreakingNetherrack implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE && e.getBlock().getWorld().getName().equals("world_nether")) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
        }
        if(e.getBlock().getBlockData().getMaterial() == Material.NETHERRACK) {
            e.setCancelled(true);
            e.getBlock().setType(Material.DEEPSLATE);
        }
    }
}
