package me.pm7.cornbreaddensened;

import me.pm7.cornbreaddensened.Generators.EndChunkGenerator;
import me.pm7.cornbreaddensened.Generators.OverworldChunkGenerator;
import me.pm7.cornbreaddensened.Objects.BlockStructure;
import me.pm7.cornbreaddensened.Objects.LoadedStructure;
import me.pm7.cornbreaddensened.annoyances.*;
import org.bukkit.Bukkit;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

public final class CornbreadDensened extends JavaPlugin {
    private static CornbreadDensened plugin;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been enabled.");

        ItemManager.init();

        plugin = this;

        ConfigurationSerialization.registerClass(LoadedStructure.class);
        ConfigurationSerialization.registerClass(BlockStructure.class);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new EndermiteSilverfishBuff(), this);
        getServer().getPluginManager().registerEvents(new NoBreakingNetherrack(), this);
        getServer().getPluginManager().registerEvents(new BigCreeperExplosion(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new FireAspectZombies(), this);
        getServer().getPluginManager().registerEvents(new RandomNetherSpeed(), this);
        getServer().getPluginManager().registerEvents(new NoOverworldDrops(), this);
        getServer().getPluginManager().registerEvents(new ZombiePiglinAgro(), this);
        getServer().getPluginManager().registerEvents(new ExplodeOnCraft(), this);
        getServer().getPluginManager().registerEvents(new InventoryClear(), this);
        getServer().getPluginManager().registerEvents(new PickUpEndFrame(), this);
        getServer().getPluginManager().registerEvents(new SkeletonTotems(), this);
        getServer().getPluginManager().registerEvents(new NoPhantomFire(), this);
        getServer().getPluginManager().registerEvents(new FastInventory(), this);
        getServer().getPluginManager().registerEvents(new LessKnockback(), this);
        getServer().getPluginManager().registerEvents(new EndermanAgro(), this);
        getServer().getPluginManager().registerEvents(new NetherSpawns(), this);
        getServer().getPluginManager().registerEvents(new NoLavaBucket(), this);
        getServer().getPluginManager().registerEvents(new EternalFlame(), this);
        getServer().getPluginManager().registerEvents(new FurnaceFire(), this);
        getServer().getPluginManager().registerEvents(new FurnaceBees(), this);
        getServer().getPluginManager().registerEvents(new NoChestBoat(), this);
        getServer().getPluginManager().registerEvents(new ItemManager(), this);
        getServer().getPluginManager().registerEvents(new CraftChest(), this);
        getServer().getPluginManager().registerEvents(new MathPrompt(), this);
        getServer().getPluginManager().registerEvents(new EndSpawns(), this);
        getServer().getPluginManager().registerEvents(new StoneMobs(), this);
        getServer().getPluginManager().registerEvents(new NoCrouch(), this);
        getServer().getPluginManager().registerEvents(new NoEndBed(), this);
        getServer().getPluginManager().registerEvents(new BuffMobs(), this);
        getServer().getPluginManager().registerEvents(new Hoglins(), this);
        getServer().getPluginManager().registerEvents(new Pumpkin(), this);
        getServer().getPluginManager().registerEvents(new NoSleep(), this);
        getServer().getPluginManager().registerEvents(new Leaves(), this);

        // I have removed so much efficiency because ONE OF THESE BREAKS THE ENTIRE THING AND I DON'T KNOW WHICH ONE
        new BukkitRunnable() {
            @Override
            public void run() {FallingAnvils.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {NoDiamondArmor.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {NoSprint.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {PufferDrop.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {NoOffHand.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {FastInventory.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {NetherPhantoms.Run();}
        }.runTaskTimer(plugin, 20L, 1L);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Cornbread Densened has been disabled.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        getLogger().log(Level.WARNING, "CustomChunkGenerator is used for " + worldName + " " + id);
        if(worldName.equals("world")) { return new OverworldChunkGenerator(); }
        return new EndChunkGenerator();
    }

    public static CornbreadDensened getPlugin() {
        return plugin;
    }
}
