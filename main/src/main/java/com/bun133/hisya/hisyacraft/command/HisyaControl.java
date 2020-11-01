package com.bun133.hisya.hisyacraft.command;

import com.bun133.hisya.hisyacraft.HisyaCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HisyaControl implements CommandExecutor {
    public HisyaCraft craft;

    public HisyaControl(HisyaCraft hisyaCraft) {
        this.craft = hisyaCraft;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp()) {
                player.sendMessage("You don't enough permission.");
                return true;
            }
        }
        return onRun(args);
    }

    private boolean onRun(String[] args) {
        switch (args[0]) {
            case "start":
            case "s":
                craft.manager.isHisya = true;
                break;
            case "end":
            case "e":
                craft.manager.isHisya = false;
                craft.manager.isNeo = false;
                break;
            case "neo":
            case "n":
                craft.manager.isNeo = true;
                break;
        }
        return false;
    }
}
