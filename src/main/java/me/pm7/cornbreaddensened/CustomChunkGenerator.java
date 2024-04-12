package me.pm7.cornbreaddensened;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.structure.Structure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {
    CornbreadDensened plugin = CornbreadDensened.getPlugin();
    private final FastNoiseLite stoneLayer = new FastNoiseLite();
    private final FastNoiseLite chromeSpikes = new FastNoiseLite();
    private final FastNoiseLite chromeGround = new FastNoiseLite();

    // TODO: Try loading all the NBT files up here and only placing them down there

    public CustomChunkGenerator() {
        Random random = new Random();
        // Setup all the noisemaps with random seeds
        stoneLayer.SetSeed((int)Math.floor(random.nextFloat() * (99999)));
        chromeSpikes.SetSeed((int)Math.floor(random.nextFloat() * (99999)));
        chromeGround.SetSeed((int)Math.floor(random.nextFloat() * (99999)));

        stoneLayer.SetFrequency(0.001f);
        chromeSpikes.SetFrequency(0.05f);

        chromeGround.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        chromeGround.SetFrequency(0.038f);
        chromeGround.SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.EuclideanSq);
        chromeGround.SetCellularReturnType(FastNoiseLite.CellularReturnType.CellValue);
        chromeGround.SetCellularJitter(1.01f);
        chromeGround.SetDomainWarpType(FastNoiseLite.DomainWarpType.OpenSimplex2);
        chromeGround.SetDomainWarpAmp(50);
    }

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        Random chunkColor = new Random((long) ((int) (chunkX / 3) + 18344) * ((int) (chunkZ / 3) + 28644) * 48743 + worldInfo.getSeed());
        int color = (int) (chunkColor.nextFloat()*16);

        // First order of business, Delete around 1 in every ten chunks for funsies
        if((int)Math.floor(random.nextFloat() * (10)) == 1) {
            for(int y = chunkData.getMinHeight() + 30; y < chunkData.getMaxHeight(); y++) {
                for (int x = 0; x < 16; x++) {

                    //Set Z 1
                    float ground = (((this.chromeGround.GetNoise(x + (chunkX * 16), (chunkZ * 16)) * 2) * 20) + 60);
                    float stone = (((this.stoneLayer.GetNoise(x + (chunkX * 16), (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                    if (y < ground || y < stone) { chunkData.setBlock(x, y, 0, getSurfaceMat(color, random)); }

                    //Set Z 16
                    ground = (((this.chromeGround.GetNoise(x + (chunkX * 16), 15 + (chunkZ * 16)) * 2) * 20) + 60);
                    stone = (((this.stoneLayer.GetNoise(x + (chunkX * 16), 15 + (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                    if (y < ground || y < stone) { chunkData.setBlock(x, y, 15, getSurfaceMat(color, random)); }
                }
                for (int z = 0; z < 16; z++) {

                    //Set X 1
                    float ground = (((this.chromeGround.GetNoise((chunkX * 16), z + (chunkZ * 16)) * 2) * 20) + 60);
                    float stone = (((this.stoneLayer.GetNoise((chunkX * 16), z + (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                    if (y < ground || y < stone) { chunkData.setBlock(0, y, z, getSurfaceMat(color, random)); }

                    //Set X 16
                    ground = (((this.chromeGround.GetNoise(15 + (chunkX * 16), z + (chunkZ * 16)) * 2) * 20) + 60);
                    stone = (((this.stoneLayer.GetNoise(15 + (chunkX * 16), z + (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                    if (y < ground || y < stone) { chunkData.setBlock(15, y, z, getSurfaceMat(color, random)); }
                }
            }
        }
        // Ok time to actually generate the normal layers now
        else {
            for (int y = chunkData.getMinHeight() + 30; y < chunkData.getMaxHeight(); y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        float stone = (((this.stoneLayer.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                        float spikes = (((this.chromeSpikes.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 35) + 50);
                        float ground = (((this.chromeGround.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 20) + 60);

                        //Generate """stone""" layer (it will in fact not be stone)
                        if (y < stone-1) { chunkData.setBlock(x, y, z, getStoneMat(y, (int) stone, random));}

                        // Make a little two-block top layer of stone as the normal stuff
                        else if (y >= stone-1 && y <= stone+2) { chunkData.setBlock(x, y, z, getSurfaceMat(color, random)); }

                        // Generate spikes of surface blocks to look cool
                        else if (y >= stone && y <= spikes && y >= ground) { chunkData.setBlock(x, y, z, getSurfaceMat(color, random)); }

                        // Generate plateau-like surfaces from that wonky noisemap
                        if (y > stone && y <= ground) {
                            chunkData.setBlock(x, y, z, getSurfaceMat(color, random));

                            // Generate "structures"
                            if(x == 8 && z == 8 && y > ground-2 && y > 63) {
                                // These structures only generate in the general middle of a chunk because

                                // Add a cool house to white chunks every once in a while (special tool that will help us later)
                                if (color == 0 && Math.floor(random.nextFloat() * (15)) == 1) {
                                    x += (chunkX * 16) - 3;
                                    y -= 2;
                                    z += (chunkZ * 16) + 3;
                                    // 3/5ths of the time, generate a normal house, otherwise, generate one with a portal frame
                                    switch ((int) Math.floor(random.nextFloat() * (5))) {
                                        case 0:
                                        case 1:
                                        case 2:
                                            loadStructure("house.nbt", x, y, z, random); break;
                                        case 3:
                                        case 4:
                                            loadStructure("house_with_portal.nbt", x, y, z, random); break;
                                    }
                                }
                                // Add some kelp towers so you don't starve
                                else if(Math.floor(random.nextFloat() * (34)) == 1) {
                                    int finalX = x + (chunkX * 16) - 3;
                                    int finalY = y+1;
                                    int finalZ = z + (chunkZ * 16) - 3;
                                    int number = ((int) Math.floor(random.nextFloat() * 3)) + 1;
                                    loadStructure("kelp_tower_" + number + ".nbt", finalX, finalY, finalZ, random); break;
                                }
                                // Might as well put in some couches too
                                else if(Math.floor(random.nextFloat() * (23)) == 1) {
                                    int finalX = x + (chunkX * 16); // -3
                                    int finalZ = z + (chunkZ * 16) - 3;
                                    int number = ((int) Math.floor(random.nextFloat() * 7)) + 1;
                                    loadStructure("couch_" + number + ".nbt", finalX, y+1, finalZ, random); break;
                                }
                            }
                            // Oh yeah we should probably have SOME wood
                            else if (y > ground-2 && y>spikes && Math.floor(random.nextFloat() * (3750)) == 1) {
                                // Around every like 3750th surface block will have a tree on top of it

                                // Randomize type of log we are using
                                String type = "oak";
                                switch ((int) Math.floor(random.nextFloat() * (8))) {
                                    case 0: type = "acacia"; break;
                                    case 1: type = "birch"; break;
                                    case 2: type = "cherry"; break;
                                    case 3: type = "dark_oak"; break;
                                    case 4: type = "jungle"; break;
                                    case 5: type = "mangrove"; break;
                                    case 6: type = "oak"; break;
                                    case 7: type = "spruce"; break;
                                }
                                // Pick variant of tree
                                int variant = ((int) Math.floor(random.nextFloat() * (5))) + 1;

                                // Spawn in the tree
                                loadStructure("trees/"+type+"_"+variant+".nbt", x + (chunkX * 16)-2, y+1, z + (chunkZ * 16)-2, random);
                            }

                            //Waterlevl
                        } else if (y > stone && y <= 63 && x % 5 != 0 && z % 5 != 0 && x % 6 != 0 && z % 6 != 0) {
                            // Decided to make the water soul sand. Deal with it
                            chunkData.setBlock(x, y, z, Material.SOUL_SAND);
                        }
                    }
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
            switch ((int)Math.floor(random.nextFloat() * (4))) {
                case 0: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random()); break;
                case 1: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.CLOCKWISE_90, Mirror.NONE, 0, 1, new Random()); break;
                case 2: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.COUNTERCLOCKWISE_90, Mirror.NONE, 0, 1, new Random()); break;
                case 3: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.CLOCKWISE_180, Mirror.NONE, 0, 1, new Random()); break;
            }
        }, 20L);
    }

    // For generating the stone layer; making inventory management a nightmare
    Material getStoneMat(int y, int height, Random random) {
        int r = (int)Math.floor(random.nextFloat() * (59));
        Material mat = Material.STONE;
        switch (r) {
            case 0: mat = Material.SMOOTH_STONE; break;
            case 1: mat = Material.STONE_BRICKS; break;
            case 2: mat = Material.CRACKED_STONE_BRICKS; break;
            case 3: mat = Material.MOSSY_COBBLESTONE; break;
            case 4: mat = Material.CHISELED_STONE_BRICKS; break;
            case 5: mat = Material.GRANITE; break;
            case 6: mat = Material.POLISHED_GRANITE; break;
            case 7: mat = Material.DIORITE; break;
            case 8: mat = Material.POLISHED_DIORITE; break;
            case 9: mat = Material.ANDESITE; break;
            case 10: mat = Material.POLISHED_ANDESITE; break;
            case 11: mat = Material.CHISELED_DEEPSLATE; break;
            case 12: mat = Material.POLISHED_DEEPSLATE; break;
            case 13: mat = Material.DEEPSLATE_BRICKS; break;
            case 14: mat = Material.CRACKED_DEEPSLATE_BRICKS; break;
            case 15: mat = Material.DEEPSLATE_TILES; break;
            case 16: mat = Material.CRACKED_DEEPSLATE_TILES; break;
            case 17: mat = Material.TUFF; break;
            case 18: mat = Material.BRICKS; break;
            case 19: mat = Material.MUD; break;
            case 20: mat = Material.GRAVEL; break;
            case 21: mat = Material.SANDSTONE; break;
            case 22: mat = Material.INFESTED_COBBLESTONE; break;
            case 23: mat = Material.CHISELED_SANDSTONE; break;
            case 24: mat = Material.RED_SANDSTONE; break;
            case 25: mat = Material.INFESTED_CRACKED_STONE_BRICKS; break;
            case 26: mat = Material.WAXED_COPPER_BLOCK; break;
            case 27: mat = Material.CHISELED_RED_SANDSTONE; break;
            case 28: mat = Material.PRISMARINE; break;
            case 29: mat = Material.DARK_PRISMARINE; break;
            case 30: mat = Material.PRISMARINE_BRICKS; break;
            case 31: mat = Material.WAXED_WEATHERED_CHISELED_COPPER; break;
            case 32: mat = Material.NETHER_BRICKS; break;
            case 33: mat = Material.CRACKED_NETHER_BRICKS; break;
            case 34: mat = Material.NETHER_WART_BLOCK; break;
            case 35: mat = Material.POLISHED_BASALT; break;
            case 36: mat = Material.POLISHED_BLACKSTONE; break;
            case 37: mat = Material.INFESTED_MOSSY_STONE_BRICKS; break;
            case 38: mat = Material.CRACKED_POLISHED_BLACKSTONE_BRICKS; break;
            case 39: mat = Material.END_STONE; break;
            case 40: mat = Material.PURPUR_PILLAR; break;
            case 41: mat = Material.PURPUR_BLOCK; break;
            case 42: mat = Material.MUSHROOM_STEM; break;
            case 43: mat = Material.INFESTED_STONE_BRICKS; break;
            case 44: mat = Material.SOUL_SOIL; break;
            case 45: mat = Material.CHISELED_QUARTZ_BLOCK; break;
            case 46: mat = Material.BUBBLE_CORAL_BLOCK; break;
            case 47: mat = Material.INFESTED_STONE; break;
            case 48: mat = Material.ROOTED_DIRT; break;
            case 49: mat = Material.DIRT; break;
            case 50: mat = Material.CALCITE; break;
            case 51: mat = Material.DRIPSTONE_BLOCK; break;
            case 52: mat = Material.OBSIDIAN; break;
            case 53: mat = Material.WAXED_WEATHERED_CUT_COPPER_STAIRS; break;
            case 54: mat = Material.SPONGE; break;
        }
        // gotta put some precious metals in. No coal though, that's what the fences were for
        if(height-y > 13 && (int)Math.floor(random.nextFloat() * (150)) == 1) { mat = Material.IRON_ORE; }
        if(height-y > 13 && (int)Math.floor(random.nextFloat() * (275)) == 1) { mat = Material.DIAMOND_ORE; }
        return mat;
    }

    Material getSurfaceMat(int color ,Random random) {
        // Group chunks into chunk chunks of 3 chunks per chunk chunk. Color them according to the magical seed random
        int block = (int)Math.floor(random.nextFloat() * (7));
        Material mat = Material.STONE;

        // Pain.
        switch (color) {
            case 0: {
                switch (block) {
                    case 0: mat = Material.WHITE_CONCRETE; break;
                    case 1: mat = Material.WHITE_TERRACOTTA; break;
                    case 2: mat = Material.WHITE_CONCRETE_POWDER; break;
                    case 3: mat = Material.WHITE_WOOL; break;
                    case 4: mat = Material.WHITE_STAINED_GLASS; break;
                    case 5: mat = Material.WHITE_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.CHISELED_QUARTZ_BLOCK; break;
                }
                break;
            }
            case 1: {
                switch (block) {
                    case 0: mat = Material.LIGHT_GRAY_CONCRETE; break;
                    case 1: mat = Material.LIGHT_GRAY_TERRACOTTA; break;
                    case 2: mat = Material.LIGHT_GRAY_CONCRETE_POWDER; break;
                    case 3: mat = Material.LIGHT_GRAY_WOOL; break;
                    case 4: mat = Material.LIGHT_GRAY_STAINED_GLASS; break;
                    case 5: mat = Material.LIGHT_GRAY_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.ANDESITE; break;
                }
                break;
            }
            case 2: {
                switch (block) {
                    case 0: mat = Material.GRAY_CONCRETE; break;
                    case 1: mat = Material.GRAY_TERRACOTTA; break;
                    case 2: mat = Material.GRAY_CONCRETE_POWDER; break;
                    case 3: mat = Material.GRAY_WOOL; break;
                    case 4: mat = Material.GRAY_STAINED_GLASS; break;
                    case 5: mat = Material.GRAY_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.DEEPSLATE_TILES; break;
                }
                break;
            }
            case 3: {
                switch (block) {
                    case 0: mat = Material.BLACK_CONCRETE; break;
                    case 1: mat = Material.BLACK_TERRACOTTA; break;
                    case 2: mat = Material.BLACK_CONCRETE_POWDER; break;
                    case 3: mat = Material.BLACK_WOOL; break;
                    case 4: mat = Material.BLACK_STAINED_GLASS; break;
                    case 5: mat = Material.BLACK_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.POLISHED_BLACKSTONE_BRICKS; break;
                }
                break;
            }
            case 4: {
                switch (block) {
                    case 0: mat = Material.BROWN_CONCRETE; break;
                    case 1: mat = Material.BROWN_TERRACOTTA; break;
                    case 2: mat = Material.BROWN_CONCRETE_POWDER; break;
                    case 3: mat = Material.BROWN_WOOL; break;
                    case 4: mat = Material.BROWN_STAINED_GLASS; break;
                    case 5: mat = Material.BROWN_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.SOUL_SOIL; break;
                }
                break;
            }
            case 5: {
                switch (block) {
                    case 0: mat = Material.RED_CONCRETE; break;
                    case 1: mat = Material.RED_TERRACOTTA; break;
                    case 2: mat = Material.RED_CONCRETE_POWDER; break;
                    case 3: mat = Material.RED_WOOL; break;
                    case 4: mat = Material.RED_STAINED_GLASS; break;
                    case 5: mat = Material.RED_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.RED_NETHER_BRICKS; break;
                }
                break;
            }
            case 6: {
                switch (block) {
                    case 0: mat = Material.ORANGE_CONCRETE; break;
                    case 1: mat = Material.ORANGE_TERRACOTTA; break;
                    case 2: mat = Material.ORANGE_CONCRETE_POWDER; break;
                    case 3: mat = Material.ORANGE_WOOL; break;
                    case 4: mat = Material.ORANGE_STAINED_GLASS; break;
                    case 5: mat = Material.ORANGE_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.SMOOTH_RED_SANDSTONE; break;
                }
                break;
            }
            case 7: {
                switch (block) {
                    case 0: mat = Material.YELLOW_CONCRETE; break;
                    case 1: mat = Material.YELLOW_TERRACOTTA; break;
                    case 2: mat = Material.YELLOW_CONCRETE_POWDER; break;
                    case 3: mat = Material.YELLOW_WOOL; break;
                    case 4: mat = Material.YELLOW_STAINED_GLASS; break;
                    case 5: mat = Material.YELLOW_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.SPONGE; break;
                }
                break;
            }
            case 8: {
                switch (block) {
                    case 0: mat = Material.LIME_CONCRETE; break;
                    case 1: mat = Material.LIME_TERRACOTTA; break;
                    case 2: mat = Material.LIME_CONCRETE_POWDER; break;
                    case 3: mat = Material.LIME_WOOL; break;
                    case 4: mat = Material.LIME_STAINED_GLASS; break;
                    case 5: mat = Material.LIME_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.EMERALD_BLOCK; break;
                }
                break;
            }
            case 9: {
                switch (block) {
                    case 0: mat = Material.GREEN_CONCRETE; break;
                    case 1: mat = Material.GREEN_TERRACOTTA; break;
                    case 2: mat = Material.GREEN_CONCRETE_POWDER; break;
                    case 3: mat = Material.GREEN_WOOL; break;
                    case 4: mat = Material.GREEN_STAINED_GLASS; break;
                    case 5: mat = Material.GREEN_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.MOSS_BLOCK; break;
                }
                break;
            }
            case 10: {
                switch (block) {
                    case 0: mat = Material.CYAN_CONCRETE; break;
                    case 1: mat = Material.CYAN_TERRACOTTA; break;
                    case 2: mat = Material.CYAN_CONCRETE_POWDER; break;
                    case 3: mat = Material.CYAN_WOOL; break;
                    case 4: mat = Material.CYAN_STAINED_GLASS; break;
                    case 5: mat = Material.CYAN_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.OXIDIZED_COPPER; break;
                }
                break;
            }
            case 11: {
                switch (block) {
                    case 0: mat = Material.LIGHT_BLUE_CONCRETE; break;
                    case 1: mat = Material.LIGHT_BLUE_TERRACOTTA; break;
                    case 2: mat = Material.LIGHT_BLUE_CONCRETE_POWDER; break;
                    case 3: mat = Material.LIGHT_BLUE_WOOL; break;
                    case 4: mat = Material.LIGHT_BLUE_STAINED_GLASS; break;
                    case 5: mat = Material.LIGHT_BLUE_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.BLUE_ICE; break;
                }
                break;
            }
            case 12: {
                switch (block) {
                    case 0: mat = Material.BLUE_CONCRETE; break;
                    case 1: mat = Material.BLUE_TERRACOTTA; break;
                    case 2: mat = Material.BLUE_CONCRETE_POWDER; break;
                    case 3: mat = Material.BLUE_WOOL; break;
                    case 4: mat = Material.BLUE_STAINED_GLASS; break;
                    case 5: mat = Material.BLUE_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.LAPIS_BLOCK; break;
                }
                break;
            }
            case 13: {
                switch (block) {
                    case 0: mat = Material.PURPLE_CONCRETE; break;
                    case 1: mat = Material.PURPLE_TERRACOTTA; break;
                    case 2: mat = Material.PURPLE_CONCRETE_POWDER; break;
                    case 3: mat = Material.PURPLE_WOOL; break;
                    case 4: mat = Material.PURPLE_STAINED_GLASS; break;
                    case 5: mat = Material.PURPLE_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.AMETHYST_BLOCK; break;
                }
                break;
            }
            case 14: {
                switch (block) {
                    case 0: mat = Material.MAGENTA_CONCRETE; break;
                    case 1: mat = Material.MAGENTA_TERRACOTTA; break;
                    case 2: mat = Material.MAGENTA_CONCRETE_POWDER; break;
                    case 3: mat = Material.MAGENTA_WOOL; break;
                    case 4: mat = Material.MAGENTA_STAINED_GLASS; break;
                    case 5: mat = Material.MAGENTA_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.PURPUR_BLOCK; break;
                }
                break;
            }
            case 15: {
                switch (block) {
                    case 0: mat = Material.PINK_CONCRETE; break;
                    case 1: mat = Material.PINK_TERRACOTTA; break;
                    case 2: mat = Material.PINK_CONCRETE_POWDER; break;
                    case 3: mat = Material.PINK_WOOL; break;
                    case 4: mat = Material.PINK_STAINED_GLASS; break;
                    case 5: mat = Material.PINK_GLAZED_TERRACOTTA; break;
                    case 6: mat = Material.CHERRY_SLAB; break;
                }
                break;
            }
        }
        return mat;
    }
}