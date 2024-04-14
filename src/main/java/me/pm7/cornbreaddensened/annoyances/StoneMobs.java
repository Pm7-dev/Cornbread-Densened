package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class StoneMobs implements Listener {
    Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if(e.getBlock().getType() != Material.STONE) { return; }
        if((int)Math.floor(random.nextFloat() * 5) != 1) { return; }

        Location loc = e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5);
        World world = loc.getWorld();
        switch ((int)Math.floor(random.nextFloat() * 8)) {
            case 0:
                Bee bee = (Bee) world.spawnEntity(loc, EntityType.BEE);
                bee.setTarget(e.getPlayer());
                bee.setAnger(99999);
                break;
            case 1:
                world.spawnEntity(loc, EntityType.CAVE_SPIDER);
                break;
            case 2:
                world.spawnEntity(loc, EntityType.SILVERFISH);
                break;
            case 3:
                world.spawnEntity(loc, EntityType.SPIDER);
                world.spawnEntity(loc, EntityType.CAVE_SPIDER);
                break;
            case 4:
                world.spawnEntity(loc, EntityType.VEX);
                break;
            case 5:
                world.spawnEntity(loc, EntityType.ENDERMITE);
                break;
            case 6:
                MagmaCube cube = (MagmaCube) world.spawnEntity(loc, EntityType.MAGMA_CUBE);
                cube.setSize(2);
                break;
            case 7:
                Slime slime = (MagmaCube) world.spawnEntity(loc, EntityType.SLIME);
                slime.setSize(2);
                break;
        }
    }
}
