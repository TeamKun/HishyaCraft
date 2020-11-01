package com.bun133.hisya.hisyacraft;

import com.bun133.hisya.util.DoubleSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionLogger extends BukkitRunnable {
    public Map<Player, Location> NowLocationLog = new HashMap<>();
    public Map<Player, Location> previousLocationLog = new HashMap<>();

    //Run Every(or 2,3) Tick
    @Override
    public void run() {
        copyToPrevious();
        Bukkit.getServer().getOnlinePlayers().forEach(this::addLog);
    }

    private void addLog(Player player) {
        NowLocationLog.put(player, player.getLocation());
    }

    private void copyToPrevious() {
        previousLocationLog = new HashMap<>(NowLocationLog);
        NowLocationLog.clear();
    }

    public boolean isPlayerMoved(Player player) {
        if (!isContain(player)) {
            return false;
        }

        DoubleSet<Location, Location> diff = getPlayerLog(player);

        return diff.getT().getBlock().getLocation().equals(
                diff.getS().getBlock().getLocation()
        );
    }

    /**
     * @param player as index player
     * @return if player is unknown, returns null
     */
    public DoubleSet<Location, Location> getPlayerLog(Player player) {
        if (isContain(player)) return null;

        return new DoubleSet<>(
                previousLocationLog.get(player),
                NowLocationLog.get(player)
        );
    }


    /**
     * @param player as Index Player
     * @return if player was recorded by this logger
     */
    public boolean isContain(Player player) {
        return previousLocationLog.containsKey(player) && NowLocationLog.containsKey(player);
    }

    public List<Player> getPlayers(){
        List<Player> list = new ArrayList<>();
        for (Map.Entry<Player,Location> en: NowLocationLog.entrySet()){
            if (isContain(en.getKey())){
                list.add(en.getKey());
            }
        }
        return list;
    }
}
