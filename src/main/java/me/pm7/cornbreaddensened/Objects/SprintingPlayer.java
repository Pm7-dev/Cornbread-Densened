package me.pm7.cornbreaddensened.Objects;

import org.bukkit.entity.Player;

public class SprintingPlayer {
    public Player player;
    public int ticks;
    
    public SprintingPlayer(Player p, int ticks) {
        this.player = p;
        this.ticks = ticks;
    }
}
