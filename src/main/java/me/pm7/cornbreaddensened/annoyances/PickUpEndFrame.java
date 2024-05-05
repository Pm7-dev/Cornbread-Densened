package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.CornbreadDensened;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Random;

public class PickUpEndFrame implements Listener {
    CornbreadDensened plugin = CornbreadDensened.getPlugin();
    int i=0;

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if(block == null) { return; }
        if(block.getType() != Material.END_PORTAL_FRAME) { return; }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) { e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Right click end portal frames to pick them up")); return; }
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {return;}
        e.setCancelled(true);
        block.setType(Material.AIR);
        Location loc = block.getLocation().add(new Vector(0.5, 0.5, 0.5));
        ItemStack frame = new ItemStack(Material.END_PORTAL_FRAME, 1);
        ItemMeta meta = frame.getItemMeta();
        meta.setDisplayName(String.valueOf(i));
        frame.setItemMeta(meta);
        loc.getWorld().dropItemNaturally(loc, frame);
        i++;

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 12) {
                    loc.getWorld().spawnEntity(loc, EntityType.SILVERFISH);
                    count++;
                } else { Bukkit.getScheduler().cancelTasks(plugin); }
            }
        }, 0L, 1L);
    }
}
