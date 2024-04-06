package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import java.util.Random;

public class FurnaceFire implements Listener {
    Random random = new Random();

    @EventHandler
    public void onFurnaceUse(FurnaceSmeltEvent e) {
        if((int)Math.floor(random.nextFloat() * 32) != 1) { return; }
        e.getBlock().setType(Material.FIRE);
    }
}
