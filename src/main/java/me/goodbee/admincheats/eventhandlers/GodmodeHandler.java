package me.goodbee.admincheats.eventhandlers;

import me.goodbee.admincheats.activelists.GodmodeList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodmodeHandler implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if(GodmodeList.isActivated(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
