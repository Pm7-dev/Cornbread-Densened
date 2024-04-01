package me.pm7.cornbreaddensened.annoyances;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class SkeletonTotems implements Listener {
    Random random = new Random();
    public void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntityType() == EntityType.SKELETON) {
            if((int) Math.floor(random.nextFloat() * (5)) == 1) {
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
            }
        }
    }

    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Entity damaged = e.getEntity();
        if (e.getDamager() instanceof Player) {
            if(damaged.getType() == EntityType.STRAY && !damaged.getPassengers().isEmpty()) {
                Player p = (Player) e.getDamager();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You can only damage the top of the totem."));
                return;
            }
            if(damaged.getType() == EntityType.SILVERFISH && !damaged.getPassengers().isEmpty() && damaged.getPassengers().get(0).getType() == EntityType.STRAY) {
                Player p = (Player) e.getDamager();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You can only damage the top of the totem."));
            }
        }
    }
}
