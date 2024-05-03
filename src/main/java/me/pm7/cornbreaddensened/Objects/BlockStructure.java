package me.pm7.cornbreaddensened.Objects;

import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("BlockStructure")
public class BlockStructure implements ConfigurationSerializable {
    public BlockData blockData;
    public int x;
    public int y;
    public int z;

    public BlockStructure(BlockData data, int x, int y, int z) {
        this.blockData = data;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("blockData", blockData.getAsString());
        data.put("x", x);
        data.put("y", y);
        data.put("z", z);
        return data;
    }

    public static BlockStructure deserialize(Map<String, Object> data) {
        String blockDataString = (String) data.get("blockData");
        BlockData blockData = Bukkit.createBlockData(blockDataString);
        int x = (int) data.get("x");
        int y = (int) data.get("y");
        int z = (int) data.get("z");
        return new BlockStructure(blockData, x, y, z);
    }
}
