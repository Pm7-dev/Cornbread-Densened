package me.pm7.cornbreaddensened;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class ItemManager implements Listener {

    public static void init() { createDensenedCornbread(); }

    private static void createDensenedCornbread() {

        // Make the item
        ItemStack item = new ItemStack(Material.DRIED_KELP);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Densened Cornbread");
        meta.setLore(Arrays.asList("It's like normal Cornbread","...but densened."));
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        // Make the recipe
        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("densened_cornbread"), item);
        recipe.addIngredient(4, Material.DRIED_KELP);
        Bukkit.getServer().addRecipe(recipe);
    }

    @EventHandler
    public void onItemEat(FoodLevelChangeEvent e) {
        ItemStack item = e.getItem();
        if(item == null || item.getItemMeta() == null) { return; }
        if(item.getItemMeta().getDisplayName().equals("Densened Cornbread")) {
            Player p = (Player) e.getEntity();
            p.setHealth(1);
            p.setFoodLevel(20);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 2, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 2, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 5, true));
        }
    }
}
