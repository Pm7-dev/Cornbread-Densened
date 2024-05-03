package me.pm7.cornbreaddensened.Generators;

import me.pm7.cornbreaddensened.Objects.LoadedStructure;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;

public class StructureGenerator {
    private static List<LoadedStructure> structureList = new ArrayList<>();
    public static void LoadStructure(String name, int x, int y, int z, ChunkGenerator.ChunkData data) {

        // If the structure has already been loaded before, just load it from the list
        for(LoadedStructure structure : structureList) {
            if(structure.name.equals(name)) {
                populateBlocks(structure, x, y, z, data);
                return;
            }
        }


    }

    private static void populateBlocks(LoadedStructure structure, int x, int y, int z, ChunkGenerator.ChunkData data) {

    }
}
