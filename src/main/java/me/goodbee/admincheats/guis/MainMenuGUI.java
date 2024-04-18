package me.goodbee.admincheats.guis;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import me.goodbee.admincheats.AdminCheats;
import me.goodbee.admincheats.activelists.GodmodeList;
import me.goodbee.admincheats.activelists.InfiniteTotemsList;
import me.goodbee.admincheats.activelists.KillauraList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        pane.bindItem('1', new GuiItem(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE), event -> event.setCancelled(true)));

        StaticPane staticPane = new StaticPane(1, 1, 7, 4,Pane.Priority.HIGHEST);

        RainbowRunner rainbowRunner = new RainbowRunner(pane, gui);
        BukkitTask rainbowTask = rainbowRunner.runTaskTimer(AdminCheats.getPlugin(), 20, 20);

        gui.setOnClose(new Consumer<InventoryCloseEvent>() {
            @Override
            public void accept(InventoryCloseEvent inventoryCloseEvent) {
                rainbowTask.cancel();
            }
        });

        addFlyItem(staticPane);
        addGodItem(staticPane);
        addInfTotemItem(staticPane, gui, pane);
        addKillAuraItem(staticPane);

        return gui;
    }

    private static void addFlyItem(StaticPane staticPane) {
        ItemStack flyStack = new ItemStack(Material.FEATHER);
        ItemMeta flyMeta = flyStack.getItemMeta();

        List<String> flyLore = new ArrayList<String>();
        flyLore.add(ChatColor.GRAY + "Allows you to fly just like in creative mode.");
        flyLore.add("");
        flyLore.add(ChatColor.RED + "" + ChatColor.ITALIC + "No settings available.");

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
    }

    private static void addGodItem(StaticPane staticPane) {
        ItemStack godStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta godMeta = godStack.getItemMeta();

        List<String> godLore = new ArrayList<String>();
        godLore.add(ChatColor.GRAY + "Makes you invincible.");
        godLore.add("");
        godLore.add(ChatColor.RED + "" + ChatColor.ITALIC + "No settings available.");

        godMeta.setDisplayName(ChatColor.YELLOW + "God Mode");
        godMeta.setLore(godLore);

        godStack.setItemMeta(godMeta);

        staticPane.addItem(new GuiItem(godStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(!GodmodeList.isActivated(player.getUniqueId())) {
                    player.sendMessage(ChatColor.GREEN + "God mode has been enabled.");

                    GodmodeList.addPlayer(player.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "God mode has been disabled.");

                    GodmodeList.removePlayer(player.getUniqueId());
                }
            }
        }), Slot.fromIndex(1));
    }

    private static void addInfTotemItem(StaticPane staticPane, ChestGui gui, PatternPane pane) {
        ItemStack infTotemsStack = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta infTotemsMeta = infTotemsStack.getItemMeta();

        List<String> infTotemLore = new ArrayList<String>();
        infTotemLore.add(ChatColor.GRAY + "Have a totem resurrect you even if you don't have one.");
        infTotemLore.add("");
        infTotemLore.add(ChatColor.RED + "" + ChatColor.ITALIC + "No settings available.");

        infTotemsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Infinite Totems");
        infTotemsMeta.setLore(infTotemLore);

        infTotemsStack.setItemMeta(infTotemsMeta);

        staticPane.addItem(new GuiItem(infTotemsStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(InfiniteTotemsList.isActivated(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Infinite Totems have been disabled.");
                    InfiniteTotemsList.removePlayer(player.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.GREEN + "Infinite Totems have been enabled.");
                    InfiniteTotemsList.addPlayer(player.getUniqueId());
                }
            }
        }), Slot.fromIndex(2));

        gui.addPane(staticPane);
        gui.addPane(pane);
    }

    private static void addKillAuraItem(StaticPane staticPane) {
        ItemStack killAuraStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta killAuraMeta = killAuraStack.getItemMeta();

        List<String> killAuraLore = new ArrayList<String>();
        killAuraLore.add(ChatColor.GRAY + "Damage entities around you.");
        killAuraLore.add("");
        killAuraLore.add(ChatColor.RED + "" + ChatColor.ITALIC + "No settings available.");

        killAuraMeta.setDisplayName(ChatColor.RED + "Kill Aura");
        killAuraMeta.setLore(killAuraLore);

        killAuraStack.setItemMeta(killAuraMeta);

        staticPane.addItem(new GuiItem(killAuraStack, new Consumer<InventoryClickEvent>() {
            @Override
            public void accept(InventoryClickEvent event) {
                event.setCancelled(true);

                Player player = (Player) event.getWhoClicked();

                if(KillauraList.isActivated(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "Kill Aura has been disabled.");
                    KillauraList.removePlayer(player.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.GREEN + "Kill Aura has been enabled.");
                    KillauraList.addPlayer(player.getUniqueId());
                }
            }
        }), Slot.fromIndex(3));
    }

    private static class RainbowRunner extends BukkitRunnable {
        public RainbowRunner(PatternPane pane, ChestGui gui) {
            this.pane = pane;
            this.gui = gui;
        }
        private PatternPane pane;
        private ChestGui gui;
        private int currentItem = 0;

        private final List<Material> items = Arrays.asList(
                Material.YELLOW_STAINED_GLASS_PANE,
                Material.GREEN_STAINED_GLASS_PANE,
                Material.BLUE_STAINED_GLASS_PANE
        );

        @Override
        public void run() {
            if(currentItem == items.toArray().length) {
                currentItem = 0;
            }

            Material nextItem = items.get(currentItem);
            pane.bindItem('1', new GuiItem(new ItemStack(nextItem), event -> event.setCancelled(true)));

            gui.update();
        }
    }
}
