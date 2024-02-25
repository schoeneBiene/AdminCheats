package me.goodbee.admincheats;

import me.goodbee.admincheats.commands.OpenCheatUICommand;
import me.goodbee.admincheats.eventhandlers.GodmodeHandler;
import me.goodbee.admincheats.eventhandlers.InfiniteTotemHandler;
import me.goodbee.admincheats.runnables.KillauraHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminCheats extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("cheats").setExecutor(new OpenCheatUICommand(this));

        getServer().getPluginManager().registerEvents(new GodmodeHandler(), this);
        getServer().getPluginManager().registerEvents(new InfiniteTotemHandler(), this);

        new KillauraHandler().runTaskTimer(this, 10, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
