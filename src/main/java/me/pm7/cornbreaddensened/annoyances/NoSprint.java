package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.SprintingPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoSprint implements Listener {
    static Random random = new Random();
    static List<SprintingPlayer> sprinters = new ArrayList<>();
    static int ticks = 0;

    public static void Run() {
        if(ticks < 2) {ticks++; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if (p.isSprinting() && !isASprinter(p)) { sprinters.add(new SprintingPlayer(p, 0)); }
            if (!p.isSprinting() && isASprinter(p)) { sprinters.remove(getSprinter(p)); }
        }

        for(SprintingPlayer sprinter : sprinters) {
            sprinter.ticks += 1;
            if(sprinter.ticks >= 2) {
                if(Math.floor(random.nextFloat() * 30) == 0) {
                    Inventory inv = sprinter.player.getInventory();
                    for(ItemStack item : inv.getContents()) {
                        if(item != null && item.getType() != Material.CARVED_PUMPKIN) {
                            double xVel = -0.5D + (random.nextFloat());
                            double zVel = -0.5D + (random.nextFloat());
                            System.out.println("xVel: " + xVel + " zVel: " + zVel);
                            Entity dropped = sprinter.player.getWorld().dropItem(sprinter.player.getLocation().clone().add(0, 0.7, 0), item);
                            dropped.setVelocity(new Vector(xVel, 0.3, zVel));
                        }
                    }
                    inv.clear();
                    sprinter.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Oh no! You were sprinting for too long and tripped!"));
                    sprinter.player.damage(1.0D);
                    sprinter.player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 3));
                    sprinter.player.setSprinting(false);
                }
            }
        }
        ticks = 0;
    }

    public static boolean isASprinter(Player p) {
        for(SprintingPlayer plr : sprinters) {
            if(plr.player == p) { return true; }
        }
        return false;
    }

    public static SprintingPlayer getSprinter(Player p) {
        for(SprintingPlayer plr : sprinters) {
            if(plr.player == p) { return plr; }
        }
        return null;
    }
}
