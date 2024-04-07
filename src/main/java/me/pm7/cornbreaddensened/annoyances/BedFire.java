package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BedFire implements Listener {
    Random random = new Random();
    List<Material> bedsList = Arrays.asList(Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED,
            Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIME_BED, Material.RED_BED,
            Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.PINK_BED, Material.PURPLE_BED,
            Material.WHITE_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.MAGENTA_BED, Material.RESPAWN_ANCHOR);

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if(e.getRespawnLocation() == e.getRespawnLocation().getWorld().getSpawnLocation()) {return;}
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        if((int)Math.floor(random.nextFloat() * 25) != 1) { return; }
        Location loc = e.getRespawnLocation();
        World world = loc.getWorld();
        boolean done = false;
        for(int x = loc.getBlockX()-2; x <loc.getBlockX()+2; x++) {
            for(int y = loc.getBlockY()-2; y <loc.getBlockY()+2; y++) {
                for(int z = loc.getBlockZ()-2; z <loc.getBlockZ()+2; z++) {
                    if(bedsList.contains(world.getBlockAt(x, y, z).getType())) {
                        if(!done) {
                            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You uhh... used your bed so hard that it uhh.. caught on fire."));
                            done = true;
                        }
                        world.getBlockAt(x, y, z).setType(Material.FIRE);
                    }
                }
            }
        }
        e.getPlayer().setRespawnLocation(null);
    }
}
