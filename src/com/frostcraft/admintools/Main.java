package com.frostcraft.admintools;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static boolean enablePhysics = true;
	public static List<UUID> vanishList = new ArrayList<UUID>(); //List of vanished players

	//Plugin enable
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockListener(),this);
		getServer().getPluginManager().registerEvents(new PlayerListener(),this);
		
		getCommand("fcadmin").setExecutor(new FCAdminCommand());
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin enabled.");
	}
	
	//Plugin disable
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[FrostCraft-AdminTools] Plugin disabled.");
	}
}
