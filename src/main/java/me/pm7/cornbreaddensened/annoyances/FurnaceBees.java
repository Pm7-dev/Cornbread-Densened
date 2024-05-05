package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FurnaceBees implements Listener {
    Random random = new Random();
    List<Material> furnaces = Arrays.asList(Material.FURNACE, Material.SMOKER, Material.BLAST_FURNACE);

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null) { return; }
        if(!furnaces.contains(e.getClickedBlock().getType())) { return; }
        if((int)Math.floor(random.nextFloat() * 20) != 1) { return; }

        Player p = e.getPlayer();
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) { return; }
        e.setCancelled(true);
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Oh no! There was a beehive in the furnace!"));
        Location loc = e.getClickedBlock().getLocation().clone().add(0.5, 0.5, 0.5);

        SpawnBee(loc, p);
        SpawnBee(loc, p);
        SpawnBee(loc, p);
    }

    void SpawnBee(Location loc, Player p) {
        World world = loc.getWorld();
        Bee bee = (Bee) world.spawnEntity(loc, EntityType.BEE);
        bee.setTarget(p);
        bee.setAnger(9999);
    }
}
