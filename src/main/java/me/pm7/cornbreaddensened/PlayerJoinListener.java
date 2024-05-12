package me.pm7.cornbreaddensened;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        World world = Bukkit.getWorld("world");
        World nether = Bukkit.getWorld("world_nether");
        World end = Bukkit.getWorld("world_the_end");

        world.setDifficulty(Difficulty.HARD);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        world.setGameRule(GameRule.SPAWN_RADIUS, 150);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);

        nether.setDifficulty(Difficulty.HARD);
        nether.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);

        end.setDifficulty(Difficulty.HARD);
        end.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
    }
}
