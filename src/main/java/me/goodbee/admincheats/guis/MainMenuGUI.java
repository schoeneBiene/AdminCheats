package me.goodbee.admincheats.guis;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import me.goodbee.admincheats.util.GodmodeMan;
import me.goodbee.admincheats.util.InfiniteTotemsMan;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class MainMenuGUI {
    public static ChestGui getGui() {
        ChestGui gui = new ChestGui(6, ChatColor.RED + "Admin Cheats GUI§§§");

        Pattern pattern = new Pattern(
                "111111111",
                "100000001",
                "100000001",
                "100000001",
                "100000001",
                "111111111"
        );

        PatternPane pane = new PatternPane(9, 6, pattern);

        pane.bindItem('1', new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), event -> event.setCancelled(true)));

        StaticPane staticPane = new StaticPane(1, 1, 7, 4,Pane.Priority.HIGHEST);

        ItemStack flyStack = new ItemStack(Material.FEATHER);
        ItemMeta flyMeta = flyStack.getItemMeta();

        flyMeta.setDisplayName(ChatColor.AQUA + "Fly");

        flyStack.setItemMeta(flyMeta);

        staticPane.addItem(new GuiItem(flyStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(player.getAllowFlight()) {
                    player.sendMessage(ChatColor.RED + "Flight has been disabled.");
                    player.setAllowFlight(false);
                } else {
                    player.sendMessage(ChatColor.GREEN + "Flight has been enabled.");
                    player.setAllowFlight(true);
                }
            }
        }), Slot.fromIndex(0));

        ItemStack godStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta godMeta = godStack.getItemMeta();

        godMeta.setDisplayName(ChatColor.YELLOW + "God Mode");

        godStack.setItemMeta(godMeta);

        staticPane.addItem(new GuiItem(godStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(!GodmodeMan.isGodded(player.getUniqueId())) {
                    player.sendMessage(ChatColor.GREEN + "God mode has been enabled.");

                    GodmodeMan.addGoddedPlayer(player.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "God mode has been disabled.");

                    GodmodeMan.removeGoddedPlayer(player.getUniqueId());
                }
            }
        }), Slot.fromIndex(1));

        ItemStack infTotemsStack = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta infTotemsMeta = infTotemsStack.getItemMeta();

        infTotemsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Infinite Totems");

        infTotemsStack.setItemMeta(infTotemsMeta);

        staticPane.addItem(new GuiItem(infTotemsStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(InfiniteTotemsMan.isActivated(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Infinite Totems have been disabled.");
                    InfiniteTotemsMan.removePlayer(player.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.GREEN + "Infinite Totems have been enabled.");
                    InfiniteTotemsMan.addPlayer(player.getUniqueId());
                }
            }
        }), Slot.fromIndex(2));

        gui.addPane(staticPane);
        gui.addPane(pane);

        return gui;
    }
}
