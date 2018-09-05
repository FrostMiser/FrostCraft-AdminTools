package com.frostcraft.admintools;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;

public final class Main extends JavaPlugin {

	static boolean enablePhysics = true;
	static List<UUID> verifiedList = new ArrayList<UUID>(); //List of verified players
	static List<UUID> vanishList = new ArrayList<UUID>(); //List of vanished players
	static List<UUID> muteList = new ArrayList<UUID>(); //List of muted players

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockListener(),this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this),this);
		
		getCommand("verify").setExecutor(new VerifyCommand(this));
		getCommand("fcadmin").setExecutor(new FCAdminCommand(this));
		
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Plugin enabled.");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Plugin disabled.");
	}
}