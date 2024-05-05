package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class SkeletonTotems implements Listener {
    Random random = new Random();

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntityType() == EntityType.SKELETON) {
            if((int) Math.floor(random.nextFloat() * (4)) == 1) {
                World world = e.getEntity().getWorld();
                Location location = e.getEntity().getLocation();
                Entity silverfish = world.spawnEntity(location, EntityType.SILVERFISH);
                Entity passenger1 = world.spawnEntity(location, EntityType.STRAY);
                Entity passenger2 = world.spawnEntity(location, EntityType.STRAY);
                Entity passenger3 = world.spawnEntity(location, EntityType.STRAY);
                Entity passenger4 = world.spawnEntity(location, EntityType.STRAY);

                passenger3.addPassenger(passenger4);
                passenger2.addPassenger(passenger3);
                passenger1.addPassenger(passenger2);
                silverfish.addPassenger(passenger1);

                PotionEffect effect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1);
                ((LivingEntity) passenger1).addPotionEffect(effect);
                ((LivingEntity) passenger2).addPotionEffect(effect);
                ((LivingEntity) passenger3).addPotionEffect(effect);
                ((LivingEntity) passenger4).addPotionEffect(effect);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Entity damaged = e.getEntity();
        if (e.getDamager() instanceof Player) {
            if(damaged.getType() == EntityType.STRAY && !damaged.getPassengers().isEmpty()) {
                Player p = (Player) e.getDamager();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You can only damage the top of the totem."));
                e.setCancelled(true);
                return;
            }
            if(damaged.getType() == EntityType.SILVERFISH && !damaged.getPassengers().isEmpty() && damaged.getPassengers().get(0).getType() == EntityType.STRAY) {
                Player p = (Player) e.getDamager();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "You can only damage the top of the totem."));
                e.setCancelled(true);
            }
        }
    }
}
