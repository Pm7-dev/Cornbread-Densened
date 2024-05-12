package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class NoBreakingNetherrack implements Listener {
    Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE && e.getBlock().getWorld().getName().equals("world_nether")) {
            e.setCancelled(true);
            if((int)Math.floor(random.nextFloat() * 12) == 1) {
                e.getBlock().setType(Material.LAVA);
            }
            else {
                e.getBlock().setType(Material.AIR);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.NETHERRACK));
            }
        }
        if(e.getBlock().getBlockData().getMaterial() == Material.NETHERRACK) {
            e.setCancelled(true);
            e.getBlock().setType(Material.DEEPSLATE);
        }
    }
}
