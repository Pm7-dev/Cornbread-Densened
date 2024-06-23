package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FireAspectZombies implements Listener {
    Random random = new Random();

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e ) {
        if(e.getEntity().getType() != EntityType.ZOMBIE) { return; }
        if(Math.floor(random.nextFloat() * (3)) != 1) { return; }

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        item.addEnchantment(Enchantment.FIRE_ASPECT, 1);

        LivingEntity entity = (LivingEntity) e.getEntity();
        if(entity.getEquipment() == null) { return; }
        entity.getEquipment().setItemInMainHand(item);
    }
}
