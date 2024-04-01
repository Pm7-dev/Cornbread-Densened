package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class Leaves implements Listener {
    List<Material> leaves = Arrays.asList(Material.ACACIA_LEAVES, Material.AZALEA_LEAVES, Material.BIRCH_LEAVES, Material.CHERRY_LEAVES,
            Material.OAK_LEAVES, Material.DARK_OAK_LEAVES, Material.SPRUCE_LEAVES, Material.MANGROVE_LEAVES, Material.JUNGLE_LEAVES);
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(leaves.contains(e.getBlock().getType())) {
            e.setDropItems(false);
            if((int) Math.floor(Math.random() * (5)) == 1) {
                World world = e.getBlock().getWorld();
                Entity bat = world.spawnEntity(e.getBlock().getLocation().clone().add(new Vector(0, 3, 0)), EntityType.BAT);
                Entity passenger = world.spawnEntity(e.getBlock().getLocation().clone().add(new Vector(0, 5, 0)), EntityType.WITCH);
                bat.addPassenger(passenger);
            }
        }
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);

        if((int) Math.floor(Math.random() * (5)) == 1) {
            World world = e.getBlock().getWorld();
            Entity bat = world.spawnEntity(e.getBlock().getLocation().clone().add(new Vector(0, 3, 0)), EntityType.BAT);
            Entity passenger = world.spawnEntity(e.getBlock().getLocation().clone().add(new Vector(0, 5, 0)), EntityType.WITCH);
            bat.addPassenger(passenger);

        }
    }
}
