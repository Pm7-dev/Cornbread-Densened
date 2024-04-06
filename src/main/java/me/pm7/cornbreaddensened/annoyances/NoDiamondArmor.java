package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoDiamondArmor {
    static int tick = 0;
    public static void Run() {
        if(tick<5) {tick+=1; return;}
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            PlayerInventory inv = p.getInventory();
            boolean diamondcheck = false;

            if (inv.getHelmet() != null && inv.getHelmet().getType() == Material.DIAMOND_HELMET) { diamondcheck = true; }
            if (inv.getChestplate() != null && inv.getChestplate().getType() == Material.DIAMOND_CHESTPLATE) { diamondcheck = true; }
            if (inv.getLeggings() != null && inv.getLeggings().getType() == Material.DIAMOND_LEGGINGS) { diamondcheck = true; }
            if (inv.getBoots() != null && inv.getBoots().getType() == Material.DIAMOND_BOOTS) { diamondcheck = true; }

            PotionEffect effect = new PotionEffect(PotionEffectType.WITHER, 40, 1);
            if(diamondcheck) {
                p.addPotionEffect(effect);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("That diamond armor is too heavy!"));
            }
        }
        tick=0;
    }
}
