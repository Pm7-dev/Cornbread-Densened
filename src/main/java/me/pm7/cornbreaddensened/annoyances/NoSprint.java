package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.SprintingPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class NoSprint implements Listener {
    static List<SprintingPlayer> sprinters = new ArrayList<>();
    static int ticks = 0;

    public static void Run() {
        if(ticks < 3) {ticks++; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (p.isSprinting() && !isASprinter(p)) { sprinters.add(new SprintingPlayer(p, 0)); }
            if (!p.isSprinting() && isASprinter(p)) { sprinters.remove(getSprinter(p)); }
        }

        for(SprintingPlayer sprinter : sprinters) {
            sprinter.ticks += 1;
            if(sprinter.ticks >= 15) {
                if(Math.floor(Math.random() * 15) == 0) {
                    Inventory inv = sprinter.player.getInventory();
                    for(ItemStack item : inv.getContents()) {
                        if(item != null) {
                            double xVel = -0.2D + (Math.random() * (0.2D*2));
                            double zVel = -0.2D + (Math.random() * (0.2D*2));
                            System.out.println("xVel: " + xVel + " zVel: " + zVel);
                            Entity dropped = sprinter.player.getWorld().dropItem(sprinter.player.getLocation(), item);
                            dropped.setVelocity(new Vector(xVel, 0.3, zVel));
                        }
                    }
                    inv.clear();
                    sprinter.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Oh no! You were sprinting for too long and tripped!"));
                    sprinter.player.damage(1.0D);
                    sprinter.player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 3));
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
