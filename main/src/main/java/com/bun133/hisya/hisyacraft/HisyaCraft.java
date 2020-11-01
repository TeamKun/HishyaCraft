package com.bun133.hisya.hisyacraft;

import com.bun133.hisya.hisyacraft.command.HisyaControl;
import org.bukkit.plugin.java.JavaPlugin;

import static com.bun133.hisya.util.JavaPluginExtentionKt.addCommand;
import static com.bun133.hisya.util.JavaPluginExtentionKt.addLoopTimer;

public final class HisyaCraft extends JavaPlugin {
    public HisyaCraftManager manager;
    public PositionLogger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = new PositionLogger();
        addLoopTimer(this,logger,1,1);

        manager = new HisyaCraftManager(this,logger);
        addCommand(this,"hc",new HisyaControl(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
