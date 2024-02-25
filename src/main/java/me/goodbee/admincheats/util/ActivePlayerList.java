package me.goodbee.admincheats.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivePlayerList {
    public static List<UUID> list = new ArrayList<UUID>();

    public static void addPlayer(UUID uuid) {
        list.add(uuid);
    }

    public static void removePlayer(UUID uuid) {
        list.remove(uuid);
    }

    public static boolean isActivated(UUID uuid) {
        return list.contains(uuid);
    }
}
