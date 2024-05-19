package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class FallingAnvils {
    static Random random = new Random();
    static CornbreadDensened plugin = CornbreadDensened.getPlugin();
    static int tick = 0;
    static int needed = (int) Math.floor((random.nextFloat() * (6500 - 5000)) + 5000);
    public static void Run() {
        if(tick < needed) { tick+=1; return; }
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            World world = p.getWorld();
            if(!world.getName().equals("world")) { continue; }
            Location loc = p.getLocation().clone().add(0, 26, 1);
            Location loc1 = loc.clone().add(new Vector(-1, 0, -1));
            Location loc2 = loc.clone().add(new Vector(0, 0, -1));
            Location loc3 = loc.clone().add(new Vector(1, 0, -1));
            Location loc4 = loc.clone().add(new Vector(-1, 0, 0));
            Location loc5 = loc.clone().add(new Vector(1, 0, 0));
            Location loc6 = loc.clone().add(new Vector(-1, 0, 1));
            Location loc7 = loc.clone().add(new Vector(0, 0, 1));
            Location loc8 = loc.clone().add(new Vector(1, 0, 1));
            spawnAnvil(world, loc);
            spawnAnvil(world, loc1);
            spawnAnvil(world, loc2);
            spawnAnvil(world, loc3);
            spawnAnvil(world, loc4);
            spawnAnvil(world, loc5);
            spawnAnvil(world, loc6);
            spawnAnvil(world, loc7);
            spawnAnvil(world, loc8);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Look out!")), 7L);
        }
        tick = 0;
        if(Math.floor(random.nextFloat() * (15)) == 1) {needed = 60;}
        else {needed = (int) Math.floor((random.nextFloat() * (6500 - 5000)) + 5000);}
    }

    static void spawnAnvil(World world, Location loc) {
        FallingBlock block = world.spawnFallingBlock(loc, Material.ANVIL.createBlockData());
        block.setCancelDrop(true);
        block.setDropItem(false);
        block.setDamagePerBlock(20.0f);
        block.setMaxDamage(99999);
        block.setHurtEntities(true);
    }
}
