package me.pm7.cornbreaddensened.annoyances;

import jdk.tools.jlink.internal.Platform;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Nethermites {
    static int tick = 0;
    public static void Run() {
        if(tick < 2400) { tick+=1; return; }
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(p.getWorld().getName().equals("world_nether")) {
                Location loc = p.getLocation();
                World world = p.getWorld();
                world.spawnEntity(loc, EntityType.ENDERMITE);
                world.spawnEntity(loc, EntityType.ENDERMITE);
            }
        }
        tick = 0;
    }
}
