package me.pm7.cornbreaddensened;

import me.pm7.cornbreaddensened.annoyances.*;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public final class CornbreadDensened extends JavaPlugin {
    private static CornbreadDensened plugin;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been enabled.");
        plugin = this;

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

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int tick = 0;
            @Override
            public void run() {
                tick++;
                if(tick%2400==0) {
                    Nethermites.Run();
                }
            }
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
