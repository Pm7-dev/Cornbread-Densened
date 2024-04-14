package me.pm7.cornbreaddensened;

import me.pm7.cornbreaddensened.Commands.start;
import me.pm7.cornbreaddensened.annoyances.*;
import org.bukkit.Bukkit;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public final class CornbreadDensened extends JavaPlugin {
    private static CornbreadDensened plugin;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been enabled.");

        plugin = this;

        getServer().getPluginManager().registerEvents(new EndermiteSilverfishBuff(), this);
        getServer().getPluginManager().registerEvents(new NoBreakingNetherrack(), this);
        getServer().getPluginManager().registerEvents(new BigCreeperExplosion(), this);
        getServer().getPluginManager().registerEvents(new RandomNetherSpeed(), this);
        getServer().getPluginManager().registerEvents(new NoOverworldDrops(), this);
        getServer().getPluginManager().registerEvents(new ZombiePiglinAgro(), this);
        getServer().getPluginManager().registerEvents(new EndEndermanAgro(), this);
        getServer().getPluginManager().registerEvents(new NoStandingStill(), this);
        getServer().getPluginManager().registerEvents(new ExplodeOnCraft(), this);
        getServer().getPluginManager().registerEvents(new InventoryClear(), this);
        getServer().getPluginManager().registerEvents(new PickUpEndFrame(), this);
        getServer().getPluginManager().registerEvents(new SkeletonTotems(), this);
        getServer().getPluginManager().registerEvents(new NoPhantomFire(), this);
        getServer().getPluginManager().registerEvents(new LessKnockback(), this);
        getServer().getPluginManager().registerEvents(new NetherSpawns(), this);
        getServer().getPluginManager().registerEvents(new NoLavaBucket(), this);
        getServer().getPluginManager().registerEvents(new EternalFlame(), this);
        getServer().getPluginManager().registerEvents(new FurnaceFire(), this);
        getServer().getPluginManager().registerEvents(new FurnaceBees(), this);
        getServer().getPluginManager().registerEvents(new NoChestBoat(), this);
        getServer().getPluginManager().registerEvents(new CraftChest(), this);
        getServer().getPluginManager().registerEvents(new MathPrompt(), this);
        getServer().getPluginManager().registerEvents(new EndSpawns(), this);
        getServer().getPluginManager().registerEvents(new StoneMobs(), this);
        getServer().getPluginManager().registerEvents(new NoCrouch(), this);
        getServer().getPluginManager().registerEvents(new NoEndBed(), this);
        getServer().getPluginManager().registerEvents(new BuffMobs(), this);
        getServer().getPluginManager().registerEvents(new Hoglins(), this);
        getServer().getPluginManager().registerEvents(new NoSleep(), this);
        getServer().getPluginManager().registerEvents(new Leaves(), this);
        getCommand("start").setExecutor(new start());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Nethermites.Run();
            FallingAnvils.Run();
            NoDiamondArmor.Run();
            NoSprint.Run();
            PufferDrop.Run();
            SpawnThingsAroundPlayers.Run();
            NoOffHand.Run();
            NoStandingStill.Run();
            NetherPhantoms.Run();
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
