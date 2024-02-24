package me.goodbee.admincheats.guis;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
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

        gui.addPane(staticPane);
        gui.addPane(pane);

        return gui;
    }
}
