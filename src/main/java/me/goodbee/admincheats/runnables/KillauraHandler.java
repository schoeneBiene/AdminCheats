package me.goodbee.admincheats.runnables;

import me.goodbee.admincheats.activelists.KillauraList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class KillauraHandler extends BukkitRunnable {
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(KillauraList.isActivated(player.getUniqueId())) {
                for(Entity entity : player.getNearbyEntities(5, 5 ,5)) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;

                        player.attack(livingEntity);
                    }
                }
            }
        }
    }
}
