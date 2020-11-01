package com.bun133.hisya.util

import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

fun addCommand(plugin: JavaPlugin,name:String,extractor:CommandExecutor){
    plugin.getCommand(name)?.setExecutor(extractor)
}

fun addListener(plugin:JavaPlugin,listener:Listener){
    plugin.server.pluginManager.registerEvents(listener,plugin)
}

fun addLoopTimer(plugin:JavaPlugin,runnable:BukkitRunnable,delay:Long,period:Long){
    runnable.runTaskTimer(plugin,delay,period)
}