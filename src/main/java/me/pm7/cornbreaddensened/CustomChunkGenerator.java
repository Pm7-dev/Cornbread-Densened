package me.pm7.cornbreaddensened;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.structure.Structure;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {
    CornbreadDensened plugin = CornbreadDensened.getPlugin();
    private final FastNoiseLite stoneLayer = new FastNoiseLite();
    private final FastNoiseLite chromeSpikes = new FastNoiseLite();
    private final FastNoiseLite chromeGround = new FastNoiseLite();

    // TODO: Try loading all the NBT files up here and only placing them down there

    public CustomChunkGenerator() {
        // Setup all the noisemaps with random seeds
        stoneLayer.SetSeed((int)Math.floor(Math.random() * (99999)));
        chromeSpikes.SetSeed((int)Math.floor(Math.random() * (99999)));
        chromeGround.SetSeed((int)Math.floor(Math.random() * (99999)));

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
        //TODO: USE THE INPUT RANDOM INSTEAD OF CREATING 30 OF THEM


        // First order of business, Delete around 1 in every ten chunks for funsies
        if((int)Math.floor(Math.random() * (10)) == 1) {
            for(int y = chunkData.getMinHeight(); y < chunkData.getMaxHeight(); y++) {
                for (int x = 0; x < 16; x++) {
                    //set z 0 and 15
                    float chromeGround = (((this.chromeGround.GetNoise(x + (chunkX * 16), (chunkZ * 16)) * 2) * 20) + 70); // 20 + 70
                    float stone = (((this.stoneLayer.GetNoise(x + (chunkX * 16), (chunkZ * 16)) * 2) * 20) + 70); // 20 + 70
                    if (y < chromeGround || y < stone) { chunkData.setBlock(x, y, 0, getSurfaceMat(chunkX, chunkZ)); }
                    chromeGround = (((this.chromeGround.GetNoise(x + (chunkX * 16), 15 + (chunkZ * 16)) * 2) * 20) + 70);
                    stone = (((this.stoneLayer.GetNoise(x + (chunkX * 16), 15 + (chunkZ * 16)) * 2) * 20) + 70);
                    if (y < chromeGround || y < stone) { chunkData.setBlock(x, y, 15, getSurfaceMat(chunkX, chunkZ)); }
                }
                for (int z = 0; z < 16; z++) {
                    //set x 0 and 15
                    float chromeGround = (((this.chromeGround.GetNoise((chunkX * 16), z + (chunkZ * 16)) * 2) * 20) + 70);
                    if (y < chromeGround) { chunkData.setBlock(0, y, z, getSurfaceMat(chunkX, chunkZ)); }
                    chromeGround = (((this.chromeGround.GetNoise(15 + (chunkX * 16), (chunkZ * 16)) * 2) * 20) + 70);
                    if (y < chromeGround) { chunkData.setBlock(15, y, z, getSurfaceMat(chunkX, chunkZ)); }
                }
            }
        }
        // Ok time to actually generate the nromal layers now
        else {
            for (int y = chunkData.getMinHeight(); y < chunkData.getMaxHeight(); y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {

                        //Generate """stone""" layer (it will in fact not be stone)
                        float stone = (((stoneLayer.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 25) + 25); // 30) + 60
                        if (y <= stone) { chunkData.setBlock(x, y, z, getStoneMat(y, (int) stone)); }

                        // Generate spikes of surface blocks to look cool
                        float spikes = (((this.chromeSpikes.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 35) + 50);
                        if (y > stone && y <= spikes) { chunkData.setBlock(x, y, z, getSurfaceMat(chunkX, chunkZ)); }

                        // Generate plateau-like surfaces from that wonky noisemap
                        float chromeGround = (((this.chromeGround.GetNoise(x + (chunkX * 16), z + (chunkZ * 16)) * 2) * 20) + 60);
                        if (y > stone && y < chromeGround) {
                            chunkData.setBlock(x, y, z, getSurfaceMat(chunkX, chunkZ));

                            // Generate "structures"
                            if(x==8 && z==8 && y > chromeGround-2 && y>spikes) {
                                // These structures only generate in the general middle of a chunk because

                                // Add a cool house to white chunks every once in a while (special tool that will help us later)
                                Random colorRand = new Random((long) ((int) (chunkX/3) + 27) * ((int) (chunkZ/3) + 3) * 4875 + 453);
                                int color = (int) (colorRand.nextFloat()*16);
                                if (color == 0 && Math.floor(Math.random() * (17)) == 1) {
                                    x += (chunkX * 16) - 3;
                                    y -= 2;
                                    z += (chunkZ * 16) + 3;
                                    // 3/5ths of the time, generate a normal house, otherwise, generate one with a portal frame
                                    switch ((int) Math.floor(Math.random() * (5))) {
                                        case 0:
                                        case 1:
                                        case 2:
                                            loadStructure("house.nbt", x, y, z); break;
                                        case 3:
                                        case 4:
                                            loadStructure("house_with_portal.nbt", x, y, z); break;
                                    }
                                }
                                // Add some kelp towers so you don't starve
                                else if(Math.floor(Math.random() * (30)) == 1) {
                                    int finalX = x + (chunkX * 16) - 3;
                                    int finalY = y + 2;
                                    int finalZ = z + (chunkZ * 16) - 3;
                                    switch ((int) Math.floor(Math.random() * (3))) {
                                        case 0: loadStructure("kelp_tower_1.nbt", finalX, finalY, finalZ); break;
                                        case 1: loadStructure("kelp_tower_2.nbt", finalX, finalY, finalZ); break;
                                        case 2: loadStructure("kelp_tower_3.nbt", finalX, finalY, finalZ); break;
                                    }
                                }
                                // Might as well put in some couches too
                                else if(Math.floor(Math.random() * (23)) == 1) {
                                    int finalX = x + (chunkX * 16); // -3
                                    int finalZ = z + (chunkZ * 16) - 3;
                                    switch ((int) Math.floor(Math.random() * (7))) {
                                        case 0: loadStructure("couch_1.nbt", finalX, y, finalZ); break;
                                        case 1: loadStructure("couch_2.nbt", finalX, y, finalZ); break;
                                        case 2: loadStructure("couch_3.nbt", finalX, y, finalZ); break;
                                        case 3: loadStructure("couch_4.nbt", finalX, y, finalZ); break;
                                        case 4: loadStructure("couch_5.nbt", finalX, y, finalZ); break;
                                        case 5: loadStructure("couch_6.nbt", finalX, y, finalZ); break;
                                        case 6: loadStructure("couch_7.nbt", finalX, y, finalZ); break;
                                    }
                                }
                            }
                            // Oh yeah we should probably have SOME wood
                            else if (y > chromeGround-2 && y>spikes && Math.floor(Math.random() * (3800)) == 1) {
                                // Around every like 4000th surface block will have a tree on top of it

                                // Randomize type of log we are using
                                String type = "oak";
                                switch ((int) Math.floor(Math.random() * (8))) {
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
                                int variant = ((int) Math.floor(Math.random() * (5))) + 1;

                                // Spawn in the tree
                                loadStructure("trees/"+type+"_"+variant+".nbt", x + (chunkX * 16)-2, y+1, z + (chunkZ * 16)-2);
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

    void loadStructure(String filename, int x, int y, int z) {
        //TODO: MAKE AN INPUT RANDOM INSTEAD OF MAKING A NEW ONE

        // Wait 20 ticks to make sure the chunk loaded
        if(!plugin.isEnabled()) {
            System.out.println("AA, a crash!");
            return;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(CornbreadDensened.getPlugin(), () -> {
            // Gather the file from the chose one
            Structure structure;
            File path = new File(plugin.getDataFolder() + "/" + filename);
            try { structure = Bukkit.getStructureManager().loadStructure(path); }
            catch (IOException e) { throw new RuntimeException(e); }

            // Choose a rotation to put the structure at
            switch ((int)Math.floor(Math.random() * (4))) {
                case 0: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random()); break;
                case 1: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.CLOCKWISE_90, Mirror.NONE, 0, 1, new Random()); break;
                case 2: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.COUNTERCLOCKWISE_90, Mirror.NONE, 0, 1, new Random()); break;
                case 3: structure.place(new Location(Bukkit.getWorld("world"), x, y, z), false, StructureRotation.CLOCKWISE_180, Mirror.NONE, 0, 1, new Random()); break;
            }
        }, 20L);
    }

    // For generating the stone layer; making inventory management a nightmare
    Material getStoneMat(int y, int height) {
        // TODO: USE INPUT RANDOM INSTEAD OF MAKING A NEW ONE

        int r = (int)Math.floor(Math.random() * (23));
        Material mat = Material.STONE;
        switch (r) {
            case 0: mat = Material.ANDESITE; break;
            case 1: mat = Material.DIORITE; break;
            case 2: mat = Material.GRANITE; break;
            case 3: mat = Material.STONE_STAIRS; break;
            case 4: mat = Material.STONE_SLAB; break;
            case 5: mat = Material.DIORITE_STAIRS; break;
            case 6: mat = Material.DIORITE_SLAB; break;
            case 7: mat = Material.GRANITE_STAIRS; break;
            case 8: mat = Material.GRANITE_SLAB; break;
            case 9: mat = Material.DIRT; break;
            case 10: mat = Material.COARSE_DIRT; break;
            case 11: mat = Material.COBWEB; break;
            case 12: mat = Material.OAK_FENCE_GATE; break;
            case 13: mat = Material.CHERRY_BUTTON; break;
            case 14: mat = Material.OBSIDIAN; break;
            case 15: mat = Material.BLACK_CONCRETE; break;
            case 16: mat = Material.DARK_OAK_FENCE; break;
            case 17: mat = Material.ACACIA_STAIRS; break;
            case 18: mat = Material.JUNGLE_SLAB; break;
            case 19: mat = Material.MAGENTA_GLAZED_TERRACOTTA; break;
            case 20: mat = Material.REDSTONE_ORE;
        }
        // gotta put some precious metals in. No coal though, that's what the fences are for
        if(height-y > 13 && (int)Math.floor(Math.random() * (175)) == 1) { mat = Material.IRON_ORE; }
        if(height-y > 13 && (int)Math.floor(Math.random() * (300)) == 1) { mat = Material.DIAMOND_ORE; }
        return mat;
    }

    Material getSurfaceMat(int chunkX, int chunkZ) {
        // TODO: USE INPUT RANDOM INSTEAD OF MAKING A NEW ONE

        // Group chunks into chunk chunks of 3 chunks per chunk chunk. Color them according to the magical seed random
        Random colorRand = new Random((long) ((int) (chunkX/3) + 27) * ((int) (chunkZ/3) + 3) * 4875 + 453);
        int color = (int) (colorRand.nextFloat()*16);
        int block = (int)Math.floor(Math.random() * (7));
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
                    case 6: mat = Material.WHITE_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.LIGHT_GRAY_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.GRAY_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.BLACK_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.BROWN_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.RED_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.ORANGE_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.YELLOW_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.LIME_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.GREEN_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.CYAN_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.LIGHT_BLUE_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.BLUE_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.PURPLE_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.MAGENTA_STAINED_GLASS_PANE; break;
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
                    case 6: mat = Material.PINK_STAINED_GLASS_PANE; break;
                }
                break;
            }
        }
        return mat;
    }
}