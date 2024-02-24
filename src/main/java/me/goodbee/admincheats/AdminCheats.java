package me.goodbee.admincheats;

import me.goodbee.admincheats.commands.OpenCheatUICommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminCheats extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("cheats").setExecutor(new OpenCheatUICommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
