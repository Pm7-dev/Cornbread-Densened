package me.pm7.cornbreaddensened.Objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SerializableAs("LoadedStructure")
public class LoadedStructure implements ConfigurationSerializable {
    public String name;
    public List<BlockStructure> blockList;

    public LoadedStructure(String name, List<BlockStructure> blockList) {
        this.name = name;
        this.blockList = blockList;
    }

    @Override
    public String toString() {
        return "name: " + name + "\nblockList: " + blockList.toString();
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("blockList", blockList);
        return data;
    }

    public static LoadedStructure deserialize(Map<String, Object> data) {
        String name = (String) data.get("name");
        List<BlockStructure> blockList = (List<BlockStructure>) data.get("blockList");
        return new LoadedStructure(name, blockList);
    }
}
