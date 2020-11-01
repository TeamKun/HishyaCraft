package com.bun133.hisya.hisyacraft;

import com.bun133.hisya.util.TripleSet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

import static com.bun133.hisya.util.JavaPluginExtentionKt.addListener;

public class HisyaCraftManager implements Listener {
    public HisyaCraft craft;
    public PositionLogger logger;
    public boolean isNeo = false;
    public boolean isHisya = false;
    public HisyaCraftManager(HisyaCraft hisyaCraft, PositionLogger logger) {
        addListener(hisyaCraft,this);
//        addLoopTimer(hisyaCraft, this, 3, 1);
        this.craft = hisyaCraft;
        this.logger = logger;
    }

    public boolean isGoing() {
        return isNeo || isHisya;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (logger.isPlayerMoved(e.getPlayer())) e.setCancelled(true);
    }

    private List<TripleSet<Player, Location, Location>> getDiffPlayers() {
        List<TripleSet<Player, Location, Location>> list = new ArrayList<>();
        for (Player p : logger.getPlayers()) {
            if (logger.isPlayerMoved(p)) {
                list.add(genDiffPlayer(p));
            }
        }
        return list;
    }

    private TripleSet<Player, Location, Location> genDiffPlayer(Player player) {
        return new TripleSet<Player, Location, Location>(player, logger.getPlayerLog(player));
    }
}
