package me.pm7.cornbreaddensened.annoyances;

import me.pm7.cornbreaddensened.Objects.IdlePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoStandingStill implements Listener {
    static List<IdlePlayer> idlePlayers = new ArrayList<>();
    static Random random = new Random();

    public static void Run() {

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() != GameMode.SURVIVAL) { continue; }
            if(MathPrompt.getStudents().contains(p.getName())) { return; }
            if(!isIdle(p)) { idlePlayers.add(new IdlePlayer(p, 0, false)); }
            IdlePlayer idle = getIdle(p);
            if(idle == null) { continue; }
            idle.ticks++;

            if(idle.ticks >= 380) {
                if(Math.floor(random.nextFloat() * 30) == 0) {
                    idle.dead = true;
                    idle.player.setHealth(0.0d);
                    idle.ticks = 0;
                }
            }
        }
    }

    public static boolean isIdle(Player p) {
        for(IdlePlayer plr : idlePlayers) {
            if(plr.player == p) { return true; }
        }
        return false;
    }

    public static IdlePlayer getIdle(Player p) {
        for(IdlePlayer plr : idlePlayers) {
            if(plr.player == p) { return plr; }
        }
        return null;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        IdlePlayer p = getIdle(e.getEntity());
        if(p == null) { return; }
        if(p.dead) {
            e.setDeathMessage(p.player.getDisplayName() + " died of... being boring for too long, idk");
            p.dead = false;
            p.ticks = 0;
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        if(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ()) { return; }
        IdlePlayer p = getIdle(e.getPlayer());
        if(p == null) { return; }
        p.ticks = 0;
        p.dead = false;
    }
}
