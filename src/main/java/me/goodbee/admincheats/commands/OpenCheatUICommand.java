package me.goodbee.admincheats.commands;

import me.goodbee.admincheats.AdminCheats;
import me.goodbee.admincheats.guis.MainMenuGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class OpenCheatUICommand implements CommandExecutor {
    private final AdminCheats plugin;
    public OpenCheatUICommand(AdminCheats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This can only be used by a player!");
            return true;
        }

        if(!(sender.hasPermission(new Permission("admincheats.gui")))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        MainMenuGUI.getGui().show((Player) sender);

        return false;
    }
}
