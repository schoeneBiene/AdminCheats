package me.goodbee.admincheats.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfiniteTotemsMan {
    public static List<UUID> activatedPlayers = new ArrayList<UUID>();

    public static void addPlayer(UUID uuid) {
        activatedPlayers.add(uuid);
    }

    public static void removePlayer(UUID uuid) {
        activatedPlayers.remove(uuid);
    }

    public static boolean isActivated(UUID uuid) {
        return activatedPlayers.contains(uuid);
    }
}
