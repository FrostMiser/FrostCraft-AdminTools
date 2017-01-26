package com.frostcraft.admintools;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[FrostCraft-AdminTools] Plugin enabled.");
	}
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[FrostCraft-AdminTools] Plugin disabled.");
	}
}
