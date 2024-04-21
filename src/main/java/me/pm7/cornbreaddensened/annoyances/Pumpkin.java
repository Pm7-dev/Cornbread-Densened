package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pumpkin implements Listener {
    final List<EntityType> pumpkinable = Arrays.asList(EntityType.ZOMBIE, EntityType.HUSK, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.SKELETON, EntityType.ZOMBIFIED_PIGLIN, EntityType.PIGLIN, EntityType.ZOMBIE_VILLAGER, EntityType.DROWNED);
    Random random = new Random();

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof LivingEntity)) { return; }
        LivingEntity damager = (LivingEntity) e.getDamager();
        if(damager.getEquipment().getItem(EquipmentSlot.HEAD) == null) { return; }
        if(damager.getEquipment().getItem(EquipmentSlot.HEAD).getType() == Material.CARVED_PUMPKIN) {
            if(!(e.getEntity() instanceof Player)) { return; }
            LivingEntity entity = (LivingEntity) e.getEntity();
            Player p = (Player) e.getEntity();
            if(entity.getEquipment().getItem(EquipmentSlot.HEAD) == null || entity.getEquipment().getItem(EquipmentSlot.HEAD).getType() != Material.CARVED_PUMPKIN) { p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("There's no real explanation for this, just avoid pumpkin mobs (or don't)")); }
            entity.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
        }
    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if(entity.getEquipment().getItem(EquipmentSlot.HEAD) == null) { return; }
        if(entity.getEquipment().getItem(EquipmentSlot.HEAD).getType() == Material.CARVED_PUMPKIN) {
            entity.getEquipment().setHelmet(new ItemStack(Material.AIR));
        }
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(!pumpkinable.contains(e.getEntity().getType())) { return; }
        if(Math.floor(random.nextFloat() * (4)) != 1) { return; }
        LivingEntity entity = (LivingEntity) e.getEntity();
        if(entity.getEquipment() == null) {return;}
        entity.getEquipment().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));
    }
}
