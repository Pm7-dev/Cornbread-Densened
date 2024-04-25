package me.pm7.cornbreaddensened.Generators;

import me.pm7.cornbreaddensened.CornbreadDensened;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.structure.Structure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class EndChunkGenerator extends ChunkGenerator {
    CornbreadDensened plugin = CornbreadDensened.getPlugin();

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                // Holes
                int trueX = chunkX*16 + x;
                int trueZ = chunkZ*16 + z;

                boolean endPillarLocation = (trueX == 70 && trueZ == 0) || (trueX == -70 && trueZ == 0) || (trueX == 0 && trueZ == 70) || (trueX == 0 && trueZ == -70) || (trueX == 50 && trueZ == 50) || (trueX == -50 && trueZ == 50) || (trueX == 50 && trueZ == -50) || (trueX == -50 && trueZ == -50);
                if(!(endPillarLocation)) {
                    if (Math.floor(random.nextFloat() * 11) == 1) { continue; }
                }

                // Not Holes
                int dist = (int) Math.sqrt(Math.pow(chunkX*16 + x, 2) + Math.pow(chunkZ*16 + z, 2));
                int maxY;
                if(dist <= 20) {maxY = 40;}
                else if(dist <= 40) { maxY = 48; }
                else if(dist <= 60) { maxY = 56; }
                else if(dist <= 80) { maxY = 64; }
                else if(dist <= 100) { maxY = 72; }
                else if(dist <= 120) { maxY = 80; }
                else if(dist <= 140) { maxY = 88; }
                else if(dist <= 160) { maxY = 96; }
                else if(dist >= 523) { maxY = 45; }
                else { continue; }

                for (int y = chunkData.getMinHeight(); y < maxY; y++) {

                    if(y==63) {
                        if(endPillarLocation) {
                            loadStructure("obsidian_tower.nbt", trueX, 63, trueZ, random);
                            continue;
                        }
                    }

                    // Uninspired Garbage
                    if(Math.floor(random.nextFloat() * 6) == 1) {chunkData.setBlock(x, y, z, Material.OBSIDIAN);}
                    else if(Math.floor(random.nextFloat() * 6) == 1) {chunkData.setBlock(x, y, z, Material.SAND);}
                    else {chunkData.setBlock(x, y, z, Material.END_STONE);}
                }
            }
        }
    }

    void loadStructure(String filename, int x, int y, int z, Random random) {
        // Wait 20 ticks to make sure the chunk loaded
        if(!plugin.isEnabled()) {
            System.out.println("AA, a crash!");
            return;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(CornbreadDensened.getPlugin(), () -> {

            // Gather the file from the chosen one
            Structure structure;
            try {
                ClassLoader classLoader = plugin.getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("structures/" + filename);
                if (inputStream == null) {
                    throw new FileNotFoundException("Resource not found: " + filename);
                }
                structure = Bukkit.getStructureManager().loadStructure(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load structure: " + filename, e);
            }

            // Choose a rotation to put the structure at
            StructureRotation rot = StructureRotation.NONE;
            int xMod = 4;
            int yMod = 4;
            switch ((int)Math.floor(random.nextFloat() * (3))) {
                case 0: rot = StructureRotation.CLOCKWISE_90; yMod = -4; break;
                case 1: rot = StructureRotation.CLOCKWISE_180; yMod = -4; xMod = -4; break;
                case 2: rot = StructureRotation.COUNTERCLOCKWISE_90; xMod = -4; break;
            }

            // Place the structure
            World world = Bukkit.getWorld("world_the_end");
            world.spawnEntity(new Location(world, x + 0.5, 105, z + 0.5), EntityType.ENDER_CRYSTAL);
            structure.place(new Location(world, x + xMod, y, z + yMod), false, rot, Mirror.NONE, 0, 1, random);
        }, 20L);
    }
}