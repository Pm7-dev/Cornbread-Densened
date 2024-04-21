package me.pm7.cornbreaddensened.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class start implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) { return true; }
        World world = Bukkit.getWorld("world");
        World nether = Bukkit.getWorld("world_nether");
        World end = Bukkit.getWorld("world_the_end");

        world.setDifficulty(Difficulty.HARD);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        world.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        world.setGameRule(GameRule.SPAWN_RADIUS, 150);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);

        nether.setDifficulty(Difficulty.HARD);
        nether.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        nether.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);

        end.setDifficulty(Difficulty.HARD);
        end.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        end.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        return true;
    }
}
