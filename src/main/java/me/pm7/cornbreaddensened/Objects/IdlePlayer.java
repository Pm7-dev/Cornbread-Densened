package me.pm7.cornbreaddensened.Objects;

import org.bukkit.entity.Player;

public class IdlePlayer {
    public Player player;
    public int ticks;
    public boolean dead;

    public IdlePlayer(Player p, int ticks, boolean dead) {
        this.player = p;
        this.ticks = ticks;
        this.dead = dead;
    }
}
