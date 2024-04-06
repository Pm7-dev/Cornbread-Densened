package me.pm7.cornbreaddensened;

import me.pm7.cornbreaddensened.annoyances.*;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class CornbreadDensened extends JavaPlugin {
    private static CornbreadDensened plugin;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been enabled.");
        plugin = this;

        World world = Bukkit.getWorld("world");
        World nether = Bukkit.getWorld("world_nether");
        World end = Bukkit.getWorld("world_the_end");

        world.setDifficulty(Difficulty.HARD);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        world.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        world.setGameRule(GameRule.SPAWN_RADIUS, 100);

        nether.setDifficulty(Difficulty.HARD);
        nether.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        nether.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        nether.setGameRule(GameRule.SPAWN_RADIUS, 100);

        end.setDifficulty(Difficulty.HARD);
        end.setGameRule(GameRule.RANDOM_TICK_SPEED, 60);
        end.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, false);
        end.setGameRule(GameRule.SPAWN_RADIUS, 100);

        getServer().getPluginManager().registerEvents(new EndSpawns(), this);
        getServer().getPluginManager().registerEvents(new EternalFlame(), this);
        getServer().getPluginManager().registerEvents(new ExplodeOnCraft(), this);
        getServer().getPluginManager().registerEvents(new InventoryClear(), this);
        getServer().getPluginManager().registerEvents(new Leaves(), this);
        getServer().getPluginManager().registerEvents(new MathPrompt(), this);
        getServer().getPluginManager().registerEvents(new NoBreakingNetherrack(), this);
        getServer().getPluginManager().registerEvents(new NoCrouch(), this);
        getServer().getPluginManager().registerEvents(new NoSleep(), this);
        getServer().getPluginManager().registerEvents(new PickUpEndFrame(), this);
        getServer().getPluginManager().registerEvents(new RandomNetherSpeed(), this);
        getServer().getPluginManager().registerEvents(new SkeletonTotems(), this);
        getServer().getPluginManager().registerEvents(new NoEndBed(), this);
        getServer().getPluginManager().registerEvents(new EndermiteSilverfishBuff(), this);
        getServer().getPluginManager().registerEvents(new NetherSpawns(), this);
        getServer().getPluginManager().registerEvents(new CraftChest(), this);
        getServer().getPluginManager().registerEvents(new NoLavaBucket(), this);
        getServer().getPluginManager().registerEvents(new BigCreeperExplosion(), this);
        getServer().getPluginManager().registerEvents(new BuffMobs(), this);
        getServer().getPluginManager().registerEvents(new StoneMobs(), this);
        getServer().getPluginManager().registerEvents(new FurnaceFire(), this);
        getServer().getPluginManager().registerEvents(new FurnaceBees(), this);
        getServer().getPluginManager().registerEvents(new BedFire(), this);
        getServer().getPluginManager().registerEvents(new ZombiePiglinAgro(), this);
        getServer().getPluginManager().registerEvents(new NoStandingStill(), this);
        getServer().getPluginManager().registerEvents(new Hoglins(), this);
        getServer().getPluginManager().registerEvents(new NoChestBoat(), this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Nethermites.Run();
            FallingAnvils.Run();
            NoDiamondArmor.Run();
            NoSprint.Run();
            PufferDrop.Run();
            SpawnThingsAroundPlayers.Run();
            NoOffHand.Run();
            NoStandingStill.Run();
        }, 20L, 1L);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been disabled.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        getLogger().log(Level.WARNING, "CustomChunkGenerator is used for " + worldName + " " + id);
        return new CustomChunkGenerator(); // Return an instance of the chunk generator we want to use.
    }

    public static CornbreadDensened getPlugin() {
        return plugin;
    }
}
