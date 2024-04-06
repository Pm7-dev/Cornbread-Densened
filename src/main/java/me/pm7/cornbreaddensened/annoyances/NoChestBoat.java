package me.pm7.cornbreaddensened.annoyances;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class NoChestBoat implements Listener {
    @EventHandler
    public void onVehicleCreate(VehicleCreateEvent e) {
        if(e.getVehicle().getType() == EntityType.CHEST_BOAT) {
            e.setCancelled(true);
        }
    }
}
