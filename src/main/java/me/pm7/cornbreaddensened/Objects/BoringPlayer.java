package me.pm7.cornbreaddensened.Objects;

import org.bukkit.entity.Player;

public class BoringPlayer {
    public Player player;
    public int ticks;
    public boolean dead, isBoring;

    public BoringPlayer(Player p, int ticks, boolean dead, boolean isBoring) {
        this.player = p;
        this.ticks = ticks;
        this.dead = dead;
        this.isBoring = isBoring;
    }
}
