package me.goodbee.admincheats.activelists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GodmodeList {
    private static List<UUID> goddedPlayers = new ArrayList<UUID>();

    public static boolean isGodded(UUID uuid) {
        return goddedPlayers.contains(uuid);
    }

    public static void addGoddedPlayer(UUID uuid) {
        goddedPlayers.add(uuid);
    }

    public static void removeGoddedPlayer(UUID uuid) {
        goddedPlayers.remove(uuid);
    }
}
