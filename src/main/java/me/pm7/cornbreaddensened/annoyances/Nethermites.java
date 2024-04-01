package me.pm7.cornbreaddensened.annoyances;

import jdk.tools.jlink.internal.Platform;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Nethermites {
    public static void Run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getWorld().getName().equals("world_nether")) {
                Location loc = p.getLocation();
                World world = p.getWorld();
                world.spawnEntity(loc, EntityType.ENDERMITE);
                world.spawnEntity(loc, EntityType.ENDERMITE);
            }
        }
    }
}
