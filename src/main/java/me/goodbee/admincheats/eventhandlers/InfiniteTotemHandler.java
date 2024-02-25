package me.goodbee.admincheats.eventhandlers;

import me.goodbee.admincheats.activelists.InfiniteTotemsList;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InfiniteTotemHandler implements Listener {
    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if(!(event.getDamage() >= player.getHealth())) return;

        if(!(InfiniteTotemsList.isActivated(player.getUniqueId()))) {
            return;
        }

        event.setDamage(0);

        player.setHealth(player.getHealth() + 1);
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1, 1);
        player.playEffect(EntityEffect.TOTEM_RESURRECT);

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 45, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 5, 2));
    }
}
